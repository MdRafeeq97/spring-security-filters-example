package com.example.paymentservice.model;

import lombok.Data;

@Data
public class PaymentRequest {
    private Double amount;
    private String currency;
    private String token;
}
