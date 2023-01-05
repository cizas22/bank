package com.example.transaction.usecase;

import com.example.transaction.dao.TransactionRepository;
import com.example.transaction.dao.Transaction;
import com.example.transaction.dao.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
@RequiredArgsConstructor
public
class InitTransactionUsecase {

    private final TransactionRepository transactionRepository;

    public Response execute(Request request) {
        var transaction = new Transaction(
                null,
                request.getAccountNo(),
                request.getCurrency(),
                request.getAmount(),
                TransactionStatus.PENDING);
        var transactionSaved = transactionRepository.save(transaction);
        return new Response(transactionSaved.getTransactionNo());
    }

    @Data
    public static class Request {
        private Long accountNo;
        private BigDecimal amount;
        private Long currency;
    }

    @Data
    @AllArgsConstructor
    public static class Response {
        private Long transactionNo;
    }
}
