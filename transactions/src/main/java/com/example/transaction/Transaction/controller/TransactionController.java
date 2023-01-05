package com.example.transaction.Transaction.controller;

import com.example.transaction.Transaction.service.TransactionService;
import com.example.transaction.Transaction.dao.Transaction;
import com.example.transaction.Transaction.usecase.ConfirmTransactionUsecase;
import com.example.transaction.Transaction.usecase.InitTransactionUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public void initTransaction(@RequestBody InitTransactionUsecase.Request req) {
        transactionService.initTransaction(req);
    }

    @PutMapping("/confirm")
    public void confirmTransaction(@RequestBody ConfirmTransactionUsecase.Request req) {
        transactionService.confirmTransaction(req);
    }

}
