package com.example.transaction.Transaction.usecase;

import com.example.transaction.Transaction.dao.Currency;
import com.example.transaction.Transaction.dao.TransactionRepository;
import com.example.transaction.Transaction.dao.Transaction;
import com.example.transaction.Transaction.dao.TransactionStatus;
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
                new Currency(request.getCurrency(), 'n'),
                request.getAmount(),
                TransactionStatus.PENDING);
        var transactionSaved = transactionRepository.save(transaction);
        return new Response(transactionSaved.getTransactionNo());
    }

    @Data
    public class Request {
        private Long accountNo;
        private BigDecimal amount;
        private Long currency;
    }

    @Data
    @AllArgsConstructor
    public class Response {
        private Long transactionNo;
    }
}
