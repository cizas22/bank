package com.example.currency.service;


import com.example.currency.dao.CurrencyRepository;
import com.example.currency.dto.CurrencyDto;
import com.example.currency.usecase.CompareCurrencyUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CurrencyService {
    private final CurrencyRepository currencyRepository;
    private final CompareCurrencyUsecase compareCurrencyUsecase;

    public List<CurrencyDto> getAllCurrencies() {
        return currencyRepository.findAll().stream().map(CurrencyDto::map).collect(Collectors.toList());
    }

    public CurrencyDto getCurrency(Long currencyNo) {
        return CurrencyDto.map(currencyRepository.findById(currencyNo).orElseThrow(() -> new RuntimeException("Currency not found")));
    }

    public CompareCurrencyUsecase.Response isCurrenciesSame(CompareCurrencyUsecase.Request req) {
        return compareCurrencyUsecase.execute(req);
    }


}
