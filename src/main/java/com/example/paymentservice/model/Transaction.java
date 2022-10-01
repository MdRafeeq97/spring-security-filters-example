package com.example.paymentservice.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Builder;
import lombok.Data;

@DynamoDBTable(tableName = "Transaction")
@Builder
@Data
public class Transaction {
    @DynamoDBHashKey(attributeName = "transactionId")
    @DynamoDBAutoGeneratedKey
    private String transactionId;

    @DynamoDBAttribute(attributeName = "nameOnCard")
    private String nameOnCard;

    @DynamoDBAttribute(attributeName = "amount")
    private Double amount;

    @DynamoDBAttribute(attributeName = "currency")
    private String currency;

    @DynamoDBAttribute(attributeName = "gateway")
    private String gateway;

    @DynamoDBAttribute(attributeName = "paymentMode")
    private String paymentMode;

    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute(attributeName = "status")
    private TransactionStatus status;

    @DynamoDBAttribute(attributeName = "patientId")
    private String patientId;

    @DynamoDBAttribute(attributeName = "doctorId")
    private String doctorId;
}
