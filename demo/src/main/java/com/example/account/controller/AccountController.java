package com.example.account.controller;

import com.example.account.service.AccountService;
import com.example.account.usecase.AccountCreditUsecase;
import com.example.account.usecase.AccountDebitUsecase;
import com.example.account.dao.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;


    @GetMapping("/{accountNo}")
    public Account getAccount(@PathVariable("accountNo") Long accountNo) {
        return accountService.getAccount(accountNo);
    }

    @GetMapping("/")
    public List<Account> getAccounts() {
        return accountService.getAllAccounts();
    }

    @PutMapping("/debit")
    public AccountDebitUsecase.Response withdraw(@RequestBody AccountDebitUsecase.Request request) {
        return accountService.debit(request);
    }

    @PutMapping("/credit")
    public AccountCreditUsecase.Response deposit(@RequestBody AccountCreditUsecase.Request req) {
        return accountService.deposit(req);
    }

}
