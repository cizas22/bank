package com.example.currency.dto;

import com.example.currency.dao.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CurrencyDto {
    private Long id;
    private char symbol;

    public static CurrencyDto map(Currency c) {
        return new CurrencyDto(c.getCurrencyNo(), c.getSymbol());
    }
}
