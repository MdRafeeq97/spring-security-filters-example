package com.example.paymentservice;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class Contoller {

    @PostMapping
    public PaymentResponse make(PaymentRequest paymentRequest) {
        return PaymentResponse.builder().amount(paymentRequest.getAmount()).build();
    }
}
