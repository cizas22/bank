package com.example.account.dto;

import lombok.Data;

import java.util.List;

@Data
public class CurrencyCompareRequestDto {
    private List<Long> currencies;
}
