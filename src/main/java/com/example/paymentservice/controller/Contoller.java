package com.example.paymentservice.controller;

import com.example.paymentservice.service.TransactionService;
import com.example.paymentservice.model.PaymentRequest;
import com.example.paymentservice.model.PaymentResponse;
import com.example.paymentservice.model.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class Contoller {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public PaymentResponse make(@RequestBody PaymentRequest paymentRequest) {
        return PaymentResponse.builder().amount(paymentRequest.getAmount())
                .transactionStatus(TransactionStatus.FAILED)
                .build();
    }

    @PostMapping("/save")
    public void save() {
        transactionService.save();
    }
}
