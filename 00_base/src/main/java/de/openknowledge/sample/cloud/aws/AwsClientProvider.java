package de.openknowledge.sample.cloud.aws;

import java.net.URI;
import java.net.URISyntaxException;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDBAsyncClient;
import software.amazon.awssdk.services.dynamodb.DynamoDBClient;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.S3Client;

public class AwsClientProvider {

	private static Region DEFAULT_REGION = Region.EU_CENTRAL_1;

	// docker run -p 4572:4572 -p 4569:4569 atlassianlabs/localstack:0.6.2

	private static String S3_ENDPOINT = "http://localhost:4572";

	private static String DYNAMODB_ENDPOINT = "http://localhost:4569";

	// blocking & synchronous clients

	public static S3Client getS3Client() {
		try {
			return S3Client.builder().credentialsProvider(AwsAccount.defaultCredentialProvider()).region(DEFAULT_REGION)
					.region(DEFAULT_REGION).endpointOverride(new URI(S3_ENDPOINT)).build();
		} catch (URISyntaxException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static DynamoDBClient getDynamoDBClient() {
		try {
			return DynamoDBClient.builder().credentialsProvider(AwsAccount.defaultCredentialProvider())
					.region(DEFAULT_REGION).endpointOverride(new URI(DYNAMODB_ENDPOINT)).build();
		} catch (URISyntaxException e) {
			throw new IllegalArgumentException(e);
		}
	}

	// non blocking & asynchronous clients

	public static S3AsyncClient getS3AsyncClient() {
		try {
			return S3AsyncClient.builder().credentialsProvider(AwsAccount.defaultCredentialProvider())
					.region(DEFAULT_REGION).endpointOverride(new URI(S3_ENDPOINT)).build();
		} catch (URISyntaxException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static DynamoDBAsyncClient getDynamoDBAsyncClient() {
		try {
			return DynamoDBAsyncClient.builder().credentialsProvider(AwsAccount.defaultCredentialProvider())
					.region(DEFAULT_REGION).endpointOverride(new URI(DYNAMODB_ENDPOINT)).build();
		} catch (URISyntaxException e) {
			throw new IllegalArgumentException(e);
		}
	}

}
