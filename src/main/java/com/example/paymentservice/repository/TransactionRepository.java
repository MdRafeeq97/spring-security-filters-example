package com.example.paymentservice.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.example.paymentservice.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionRepository {
    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public void save(Transaction transaction) {
        this.dynamoDBMapper.save(transaction);
    }
}
