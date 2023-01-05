package com.example.account.usecase;

import com.example.account.dao.AccountRepository;
import com.example.account.Account.dto.*;
import com.example.account.dto.*;
import com.example.currency.Account.dto.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountDebitUsecase {
    private final AccountRepository accountRepository;
    private final WebClient transactionWebClient;
    private final WebClient currencyWebClient;

    public Response execute(Request request) {
        var transactionInitRequest = new TransactionInitRequestDto();
        transactionInitRequest.setAccountNo(request.getAccountNo());
        transactionInitRequest.setCurrency(request.getCurrency());
        transactionInitRequest.setAmount(request.getAmount());

        var transactionInitResponse = transactionWebClient
                .post()
                .uri("/init")
                .bodyValue(transactionInitRequest)
                .retrieve().bodyToMono(TransactionInitResponseDto.class).block();

        var account = accountRepository.findByAccountNoAndStatusEqualsOpen(request.getAccountNo())
                .orElseThrow(() -> new RuntimeException("Not found or closed"));

        var currencyCompareRequest = new CurrencyCompareRequestDto();
        currencyCompareRequest.setCurrencies(List.of(request.getCurrency(), account.getCurrency()));
        var currencyCompareResponse = currencyWebClient
                .post()
                .uri("/compare-currencies")
                .bodyValue(currencyCompareRequest)
                .retrieve().bodyToMono(CurrencyCompareResponseDto.class).block();

        if (!currencyCompareResponse.isSame()) {
            throw new RuntimeException("Different debit currency");
        }

        if (account.getBalance().compareTo(request.getAmount()) == -1) {
            throw new RuntimeException("Insufficient balance");
        }
        account.setBalance(account.getBalance().subtract(request.getAmount()));
        var modifiedAccount = accountRepository.save(account);

        var transactionConfirmRequest = new TransactionConfirmRequestDto();
        transactionConfirmRequest.setTransactionNo(transactionInitResponse.getTransactionNo());
        transactionWebClient
                .put()
                .uri("/confirm")
                .bodyValue(transactionConfirmRequest);

        return new Response(modifiedAccount.getBalance());
    }

    @Data
    public static class Request {
        private Long accountNo;
        private BigDecimal amount;
        private Long currency;
    }

    @Data
    @AllArgsConstructor
    public class Response {
        private BigDecimal balance;
    }


}

