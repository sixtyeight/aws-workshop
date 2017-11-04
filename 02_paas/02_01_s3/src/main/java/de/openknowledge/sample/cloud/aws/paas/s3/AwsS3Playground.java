package de.openknowledge.sample.cloud.aws.paas.s3;

import de.openknowledge.sample.cloud.aws.AwsClientProvider;
import de.openknowledge.sample.cloud.aws.paas.s3.common.PlaygroundToggle;
import software.amazon.awssdk.AmazonClientException;
import software.amazon.awssdk.AmazonServiceException;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.sync.RequestBody;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

import static de.openknowledge.sample.cloud.aws.AwsAccount.OWNER;
import static de.openknowledge.sample.cloud.aws.paas.s3.common.PlaygroundToggle.OFF;
import static de.openknowledge.sample.cloud.aws.paas.s3.common.PlaygroundToggle.ON;


public class AwsS3Playground {


    // TODO replace "YOUR_TEMP_DIR" with a temp directory of your choice
    private final static String TEMP_DIR = "YOUR_TEMP_DIR";

    // TODO replace "YOUR_UPLOAD_FILE" with a file inside your temp directory of your choice
    private final static String UPLOAD_FILE_NAME = "YOUR_UPLOAD_FILE";

    // TODO make sure that "OWNER" is already set in project 00_common class AwsAccount
    private final static String BUCKET_NAME = OWNER + "-bucket";


    // toggles to activate / deactivate show cases
    private static final PlaygroundToggle CREATE_BUCKET = ON;
    private static final PlaygroundToggle COPY_FILE_TO_BUCKET = ON;
    private static final PlaygroundToggle LIST_BUCKETS = ON;
    private static final PlaygroundToggle COPY_FILE_FROM_BUCKET = ON;
    private static final PlaygroundToggle DELETE_ALL_FILES_FROM_BUCKET = ON;
    private static final PlaygroundToggle DELETE_BUCKET = ON;

    // AWS S3 client to acces aws API
    private S3Client s3Client;

    /**
     * Initializes and "starts" playground.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        AwsS3Playground playground = new AwsS3Playground();
        playground.playAround();

    }

    /**
     *  Initializes playground and works through the
     *  different showcases. All showcases can be turned on/off via
     *  corresponding <code>PlaygroundToggle</code>
     *
     *  <ul>
     *      <li>create bucket</li>
     *      <li>copy file to bucket</li>
     *      <li>list all buckets of account</li>
     *      <li>copy file from bucket</li>
     *      <li>delete all files in bucket</li>
     *      <li>delete bucket</li>
     *  </ul>
     */
    private void playAround() {

        String bucketName = BUCKET_NAME;
        String fileName = TEMP_DIR + UPLOAD_FILE_NAME;
        String downloadFileName = TEMP_DIR + generateUniqueFileName(UPLOAD_FILE_NAME);
        String keyName = "/documents/" + UPLOAD_FILE_NAME;

        System.out.println("===========================================");
        System.out.println("Init S3 Playground");
        System.out.println("===========================================");


        // initialize playground
        init();

        System.out.println("\n===========================================");
        System.out.println("Playground Actions");
        System.out.println("===========================================");


        try {

            if (CREATE_BUCKET.on()) {
                // create bucket of your choice
                createBucket(bucketName);
            }

            if (COPY_FILE_TO_BUCKET.on()) {
                // copy file to bucket
                copyFileToBucket(bucketName, fileName, keyName);

            }

            if (LIST_BUCKETS.on()) {
                // list all bucket of account
                listBuckets();
            }


            if (COPY_FILE_FROM_BUCKET.on()) {
                // copy file from bucket
                copyFileFromBucket(bucketName, keyName, Paths.get(URI.create("file://" + downloadFileName)));
            }

            if (DELETE_ALL_FILES_FROM_BUCKET.on()) {
                // delete all objects from bucket
                deleteObjects(bucketName);
            }

            if (DELETE_BUCKET.on()) {
                // delete bucket including bucket objects
                deleteBucket(bucketName);
            }

        }  catch (AmazonServiceException ase) {
            displayAmazonServiceException(ase);
         } catch (AmazonClientException ace) {
            displayAmazonClientException(ace);
         }
    }

    /**
     * Initializes playground.
     *
     * <ul>
     *     <li>get account specific s3 client</li>
     * </ul>
     */
    private void init() {
        s3Client = AwsClientProvider.getS3Client();
    }


    /**
     * Creates a bucket with a given name
     *
     * @param bucketName Name of bucket to create
     */
    private void createBucket(String bucketName) {

        System.out.println("\nCREATE BUCKET");
        System.out.println("===========================================\n");
        System.out.println("  REQUEST: ");
        System.out.println("  - bucket name: " + bucketName);

        /*
         * Create a new S3 bucket - Amazon S3 bucket names are globally unique,
         * so once a bucket name has been taken by any user, you can't create
         * another bucket with that same name.
         *
         * You can optionally specify a location for your bucket if you want to
         * keep your data closer to your applications or users.
         *
         * TODO create bucket
         *  - use s3Client's createBucket(...) method to create bucket
         *  - log/print out bucket location with the help of the response
         */


    }


    /**
     * Copies a file from the local file system to a s3 bucket
     *
     * @param bucketName Name of the bucket to copy the file to
     * @param fileName Full qualified file name
     * @param keyName Key of the s3 object to create
     */
    private void copyFileToBucket(String bucketName, String fileName, String keyName) {

        System.out.println("\nCOPY FILE TO BUCKET");
        System.out.println("===========================================\n");
        System.out.println("  REQUEST: ");
        System.out.println("  - bucket name: " + bucketName);
        System.out.println("  - object key: " + keyName);
        System.out.println("  - file name: " + fileName);

         /*
          * Upload an object to your bucket - You can easily upload a file to
          * S3, or upload directly an InputStream if you know the length of
          * the data in the stream. You can also specify your own metadata
          * when uploading to S3, which allows you set a variety of options
          * like content-type and content-encoding, plus additional metadata
          * specific to your applications.
          *
          * TODO copy local file to bucket
          *  - use s3Client's putObject(...) method to copy file (you will
          *    need a RequestBody of a File instance)
          *  - log/print out objects eTag with the help of the response
          */

    }

    /**
     * Lists all buckets
     */
    private void listBuckets() {

        System.out.println("\nLIST BUCKETS");
        System.out.println("===========================================\n");
        System.out.println("  REQUEST: ");
        System.out.println("  - list buckets for account ");

        /*
         * TODO list all buckets of current account
          *  - use s3Client's listBuckets(...) method to list all buckets
          *  - log/print out bucket name and creation date for each bucket
          *  - call playground listObjects method for each bucket to retrieve buckets objects
          */

    }


    /**
     * Lists all objects inside a given bucket
     *
     * @param bucketName Bucket name to list objects for
     */
    private void listObjects(String bucketName) {

         /*
          * TODO list all objects of bucket with given name
          *  - use s3Client's listObjects(...) method to list all objects
          *  - log/print out objects key, owner and last modified date
          */
    }

    /**
     * Copies a bucket object to a given file location
     *
     * @param bucketName Bucket name
     * @param keyName Unique key of object to copy
     * @param filePath Full qualified filename (including path informatio) to store the object at
     *
     */
    private void copyFileFromBucket(String bucketName, String keyName, Path filePath) {

        System.out.println("\nCOPY OBJECT FROM BUCKET");
        System.out.println("===========================================\n");
        System.out.println("  REQUEST: ");
        System.out.println("  - bucket name: " + bucketName);
        System.out.println("  - object key: " + keyName);
        System.out.println("  - filePath: " + filePath.toString());


         /*
          * Download an object - When you download an object, you get all of
          * the object's metadata and a stream from which to read the contents.
          * It's important to read the contents of the stream as quickly as
          * possibly since the data is streamed directly from Amazon S3 and your
          * network connection will remain open until you read all the data or
          * close the input stream.
          *
          * GetObjectRequest also supports several other options, including
          * conditional downloading of objects based on modification times,
          * ETags, and selectively downloading a range of an object.
          *
          *
          * TODO copy local file to bucket
          *  - use s3Client's getObject(...) method to copy file from bucket to your
          *    local file system
          *  - log/print out objects available information (if any) with the help of the response
          */

    }

    /**
     * Deletes all objects inside a given bucket
     *
     * @param bucketName Name of bucket to delete all objects from
     */
    private void deleteObjects(String bucketName) {

        System.out.println("\nDELETE ALL OBJECTS WITHIN BUCKET");
        System.out.println("===========================================\n");
        System.out.println("  REQUEST: ");
        System.out.println("  - bucket name: " + bucketName);

        /*
          *
          * TODO delete all objects within bucket
          *  - use s3Client's listObjects(...) method to retrieve all objects within a bucket
          *  - use s3Client's deleteObject(...) method to delete specific file from bucket
          *  - log/print out name of delete objects and total number of deleted objects
          */
    }


    /**
     * Deletes a bucket, given by its name
     *
     * @param bucketName Name of bucket to delete
     */
    private void deleteBucket(String bucketName) {

        System.out.println("\nDELETE BUCKET");
        System.out.println("===========================================\n");
        System.out.println("  REQUEST: ");
        System.out.println("  - bucket name: " + bucketName);


        /*
          *
          * TODO delete a single bucket
          *  - use s3Client's deleteBucket(...) method to delete specific bucket
          *  - log/print out corresponding response
          */
    }


    // ----------- PRIVATE HELPER METHODS -------------


    /**
     * Helper method to log aws specific exception of type
     * <code>AmazonServiceException</code>
     *
     * @param ex
     */
    private static void displayAmazonServiceException(AmazonServiceException ex) {
        System.out.println("Caught an AmazonServiceException, which means your request made it "
                + "to Amazon S3, but was rejected with an error response for some reason.");
        System.out.println("Error Message:    " + ex.getMessage());
        System.out.println("HTTP Status Code: " + ex.getStatusCode());
        System.out.println("AWS Error Code:   " + ex.getErrorCode());
        System.out.println("Error Type:       " + ex.getErrorType());
        System.out.println("Request ID:       " + ex.getRequestId());
    };

    /**
     * Helper method to log aws specific exception of type
     * <code>AmazonClientException</code>
     *
     * @param ex
     */
    private static void displayAmazonClientException(AmazonClientException ex) {
        System.out.println("Caught an AmazonClientException, which means the client encountered "
                + "a serious internal problem while trying to communicate with S3, "
                + "such as not being able to access the network.");
        System.out.println("Error Message: " + ex.getMessage());
    }

    /**
     * Helper method to generate a unique download file name with the
     * help of the current "timestamp"
     *
     * @param fileName non-unique name of the file
     * @return unique name of the file
     */
    private static String generateUniqueFileName(String fileName) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddss");
        Date now = new Date();
        return formatter.format(now) + "_" + fileName;
    }



}
