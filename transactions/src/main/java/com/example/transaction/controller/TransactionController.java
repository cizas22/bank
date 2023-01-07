package com.example.transaction.controller;

import com.example.transaction.service.TransactionService;
import com.example.transaction.dao.Transaction;
import com.example.transaction.usecase.ConfirmTransactionUsecase;
import com.example.transaction.usecase.InitTransactionUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping("/{accountNo}")
    public List<Transaction> getAllAccountTransactions(@PathVariable("accountNo") Long accountNo) {
        return transactionService.getAllAccountTransactions(accountNo);
    }

    @PostMapping("/init")
    public InitTransactionUsecase.Response initTransaction(@RequestBody InitTransactionUsecase.Request req) {
        return transactionService.initTransaction(req);
    }

    @PutMapping("/confirm")
    public void confirmTransaction(@RequestBody ConfirmTransactionUsecase.Request req) {
        transactionService.confirmTransaction(req);
    }

}
