package com.example.account.service;


import com.example.account.dao.AccountRepository;
import com.example.account.dao.Account;
import com.example.account.usecase.AccountCreditUsecase;
import com.example.account.usecase.AccountDebitUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountCreditUsecase accountCreditUsecase;
    private final AccountDebitUsecase accountDebitUsecase;


    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public AccountDebitUsecase.Response debit(AccountDebitUsecase.Request req) {
        return accountDebitUsecase.execute(req);
    }

    public AccountCreditUsecase.Response deposit(AccountCreditUsecase.Request req) {
        return accountCreditUsecase.execute(req);
    }

    public Account getAccount(Long accountNo) {
        return accountRepository.findById(accountNo).orElseThrow(() -> new RuntimeException("No account found"));
    }
}
