package com.example.currency.dao;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "currency")
@Data
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "currency_no")
    private Long currencyNo;

    @Column(name = "symbol", nullable = false)
    private char symbol;

}
