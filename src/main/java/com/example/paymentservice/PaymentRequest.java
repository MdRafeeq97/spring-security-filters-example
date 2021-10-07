package com.example.paymentservice;

import lombok.Data;

@Data
public class PaymentRequest {
    private Double amount;
    private String currency;
    private String token;
}
