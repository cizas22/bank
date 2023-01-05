package com.example.account.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionInitRequestDto {
    private Long accountNo;
    private BigDecimal amount;
    private Long currency;
}
