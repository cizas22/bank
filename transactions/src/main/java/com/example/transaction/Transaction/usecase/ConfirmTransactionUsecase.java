package com.example.transaction.Transaction.usecase;

import com.example.transaction.Transaction.dao.TransactionRepository;
import com.example.transaction.Transaction.dao.TransactionStatus;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfirmTransactionUsecase {
    private final TransactionRepository transactionRepository;

    public void execute(Request request) {
        var transaction = transactionRepository.findById(request.getTransactionNo()).orElseThrow(() -> new RuntimeException("No transaction found"));
        transaction.setStatus(TransactionStatus.SUCCESS);
        transactionRepository.save(transaction);
    }

    @Data
    public class Request {
        private Long transactionNo;
    }
}


