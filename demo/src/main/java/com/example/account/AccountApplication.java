package com.example.account;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AccountApplication {

    @Value("${currency.service.url}")
    private String currencyServiceUrl;

    @Value("${transaction.service.url}")
    private String transactionServiceUrl;


    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }

    @Bean
    public WebClient currencyWebClient() {
        WebClient webClient = WebClient.builder().baseUrl(currencyServiceUrl).build();
        return webClient;
    }

    @Bean
    public WebClient transactionWebClient() {
        WebClient webClient = WebClient.builder().baseUrl(transactionServiceUrl).build();
        return webClient;
    }
}
