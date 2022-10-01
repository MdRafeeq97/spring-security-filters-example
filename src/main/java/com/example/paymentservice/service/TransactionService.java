package com.example.paymentservice.service;

import com.example.paymentservice.model.Transaction;
import com.example.paymentservice.model.TransactionStatus;
import com.example.paymentservice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    public void save () {
        Transaction transaction = Transaction.builder()
                .amount(100.0)
                .currency("INR")
                .doctorId("1")
                .patientId("1")
                .paymentMode("PayU")
                .gateway("PayU")
                .status(TransactionStatus.FAILED)
                .build();
        transactionRepository.save(transaction);
    }
}
