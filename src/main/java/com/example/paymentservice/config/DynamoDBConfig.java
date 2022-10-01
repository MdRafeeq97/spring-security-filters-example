package com.example.paymentservice.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDBConfig {
    @Value("${aws.access.key}")
    private String awsAccessKey;

    @Value("${aws.access.secret}")
    private String awsAccessSecret;

    @Value("${aws.dynamodb.endpoint}")
    private String awsDynamoDbEndpoint;

    @Bean
    public DynamoDBMapper dynamoDBMapper() {
        AmazonDynamoDBClient client = new AmazonDynamoDBClient(getAwsCredentials());
        client.setEndpoint(awsDynamoDbEndpoint);
        return new DynamoDBMapper(client, DynamoDBMapperConfig.DEFAULT);
    }

    public AWSCredentials getAwsCredentials() {
        return new BasicAWSCredentials(awsAccessKey, awsAccessSecret);
    }
}
