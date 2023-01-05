package com.example.transaction.Transaction.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@AllArgsConstructor
@Table(name = "currency")
@Data
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "currency_no")
    private Long currencyNo;

    @Column(name = "symbol")
    private char symbol;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;

        return o.equals(currencyNo);
    }

    @Override
    public int hashCode() {
        return currencyNo.hashCode();
    }
}
