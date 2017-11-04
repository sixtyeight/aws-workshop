package de.openknowledge.sample.cloud.aws;

import software.amazon.awssdk.auth.AwsCredentials;
import software.amazon.awssdk.auth.AwsCredentialsProvider;
import software.amazon.awssdk.auth.StaticCredentialsProvider;

public class AwsAccount {

    public final static String OWNER = "m68k";

    private final static String ACCESS_KEY = "NOPE";

    private final static String SECRET_KEY = "NOPE";

    public static AwsCredentialsProvider defaultCredentialProvider() {
        AwsCredentialsProvider awsCredentialsProvider = new StaticCredentialsProvider(new AwsCredentials(ACCESS_KEY, SECRET_KEY));
        return awsCredentialsProvider;
    }


}
