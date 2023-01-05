package com.example.currency.controller;

import com.example.currency.service.CurrencyService;
import com.example.currency.dto.CurrencyDto;
import com.example.currency.usecase.CompareCurrencyUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/currency")
@RequiredArgsConstructor
public class CurrencyController {
    private final CurrencyService currencyService;

    @GetMapping("/{currencyNo}")
    public CurrencyDto getCurrencyByNo(@PathVariable("currencyNo") Long currencyNo) {
        return currencyService.getCurrency(currencyNo);
    }

    @GetMapping
    public List<CurrencyDto> getAllCurrencies() {
        return currencyService.getAllCurrencies();
    }

    @PostMapping("/compare-currencies")
    public CompareCurrencyUsecase.Response isCurrenciesSame(@RequestBody CompareCurrencyUsecase.Request req) {
        return currencyService.isCurrenciesSame(req);
    }
}
