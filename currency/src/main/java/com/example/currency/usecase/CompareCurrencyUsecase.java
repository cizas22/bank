package com.example.currency.usecase;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CompareCurrencyUsecase {

    public Response execute(Request req) {
        Set<Long> currenciesSet = new HashSet<>(req.getCurrencies());
        if (currenciesSet.size() != 1) {
            return new Response(false);
        }
        return new Response(true);
    }

    @Data
    public static class Request {
        private List<Long> currencies;
    }

    @Data
    @AllArgsConstructor
    public static class Response {
        private boolean isSame;
    }
}
