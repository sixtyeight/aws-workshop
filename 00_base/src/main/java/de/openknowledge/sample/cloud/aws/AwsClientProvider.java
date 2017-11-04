package de.openknowledge.sample.cloud.aws;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDBAsyncClient;
import software.amazon.awssdk.services.dynamodb.DynamoDBClient;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.S3Client;

public class AwsClientProvider {

        private static Region DEFAULT_REGION = Region.EU_CENTRAL_1;

        // blocking & synchronous clients

        public static S3Client getS3Client() {
                return S3Client.builder()
                        .credentialsProvider(AwsAccount.defaultCredentialProvider())
                        .region(DEFAULT_REGION)
                        .build();
        }

        public static DynamoDBClient getDynamoDBClient() {
                return DynamoDBClient.builder()
                        .credentialsProvider(AwsAccount.defaultCredentialProvider())
                        .region(DEFAULT_REGION)
                        .build();
        }


        // non blocking & asynchronous clients

        public static S3AsyncClient getS3AsyncClient() {
                return S3AsyncClient.builder()
                        .credentialsProvider(AwsAccount.defaultCredentialProvider())
                        .region(DEFAULT_REGION)
                        .build();
        }

        public static DynamoDBAsyncClient getDynamoDBAsyncClient() {
                return DynamoDBAsyncClient.builder()
                        .credentialsProvider(AwsAccount.defaultCredentialProvider())
                        .region(DEFAULT_REGION)
                        .build();
        }


}
