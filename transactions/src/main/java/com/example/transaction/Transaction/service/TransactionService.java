package com.example.transaction.Transaction.service;


import com.example.transaction.Transaction.dao.Transaction;
import com.example.transaction.Transaction.dao.TransactionRepository;
import com.example.transaction.Transaction.usecase.InitTransactionUsecase;
import com.example.transaction.Transaction.usecase.ConfirmTransactionUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final InitTransactionUsecase initTransactionUsecase;
    private final ConfirmTransactionUsecase confirmTransactionUsecase;

    public List<Transaction> getAllAccountTransactions(Long accountNo) {
        return transactionRepository.findAllByAccountNo(accountNo);
    }

    public InitTransactionUsecase.Response initTransaction(InitTransactionUsecase.Request req) {
        return initTransactionUsecase.execute(req);
    }

    public void confirmTransaction(ConfirmTransactionUsecase.Request req) {
        confirmTransactionUsecase.execute(req);
    }

}
