package com.example.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CurrencyDto {
    private Long id;
    private char symbol;
}
