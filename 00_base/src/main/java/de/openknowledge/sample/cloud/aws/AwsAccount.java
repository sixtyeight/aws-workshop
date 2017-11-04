package de.openknowledge.sample.cloud.aws;

import software.amazon.awssdk.auth.AwsCredentials;
import software.amazon.awssdk.auth.AwsCredentialsProvider;
import software.amazon.awssdk.auth.StaticCredentialsProvider;

public class AwsAccount {

    // TODO replace with your unique owner name
    public final static String OWNER = "YOUR_OWNER_NAME_HERE";

    // TODO replace with your AWS access key
    private final static String ACCESS_KEY = "YOUR_ACCESS_KEY";

    // TODO replace with your AWS secret key
    private final static String SECRET_KEY = "YOUR_SECRET_KEY";

    public static AwsCredentialsProvider defaultCredentialProvider() {
        AwsCredentialsProvider awsCredentialsProvider = new StaticCredentialsProvider(new AwsCredentials(ACCESS_KEY, SECRET_KEY));
        return awsCredentialsProvider;
    }


}
