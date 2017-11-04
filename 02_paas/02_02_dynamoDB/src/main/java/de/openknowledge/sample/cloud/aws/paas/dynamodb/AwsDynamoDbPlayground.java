package de.openknowledge.sample.cloud.aws.paas.dynamodb;

import de.openknowledge.sample.cloud.aws.AwsClientProvider;

import de.openknowledge.sample.cloud.aws.paas.dynamodb.common.PlaygroundToggle;
import de.openknowledge.sample.cloud.aws.paas.dynamodb.domain.Image;
import de.openknowledge.sample.cloud.aws.paas.dynamodb.domain.ImageRepository;
import software.amazon.awssdk.AmazonClientException;
import software.amazon.awssdk.AmazonServiceException;
import software.amazon.awssdk.services.dynamodb.DynamoDBAsyncClient;
import software.amazon.awssdk.services.dynamodb.DynamoDBClient;
import software.amazon.awssdk.services.dynamodb.model.*;
import software.amazon.awssdk.services.dynamodb.util.TableUtils;
import software.amazon.awssdk.utils.FunctionalUtils;

import java.io.IOException;
import java.util.*;

import static de.openknowledge.sample.cloud.aws.AwsAccount.OWNER;
import static de.openknowledge.sample.cloud.aws.paas.dynamodb.common.PlaygroundToggle.OFF;
import static de.openknowledge.sample.cloud.aws.paas.dynamodb.common.PlaygroundToggle.ON;


public class AwsDynamoDbPlayground {

    private static PlaygroundToggle LIST_TABLE = OFF;
    private static PlaygroundToggle SCAN_ITEMS = OFF;
    private static PlaygroundToggle INSERT_ITEM = OFF;
    private static PlaygroundToggle UPDATE_ITEM = OFF;
    private static PlaygroundToggle QUERY_ITEMS = OFF;
    private static PlaygroundToggle DELETE_ITEM = OFF;
    private static PlaygroundToggle DELETE_TABLE = OFF;

    private final static String TABLE_NAME = OWNER + "_images";
    private final static String KEY_COL_ID = "id";
    private final static String KEY_COL_NAME = "fileName";
    private final static String COL_TITLE = "title";
    private final static String COL_DESCRIPTION = "description";
    private final static String COL_URI = "uri";

    // image to play around with
    private final static Image PLAYGROUND_IMAGE
            = new Image("myTitle", "myDescription", "myName");

    private DynamoDBClient dynamoDBClient;
    // private DynamoDBAsyncClient dynamoDBAsyncClient;
    private ImageRepository imageRepository = new ImageRepository();

    /**
     * Initializes and "starts" playground.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        // create instance of Playground
        AwsDynamoDbPlayground playground = new AwsDynamoDbPlayground();

        // play around with dynamoDB features
        playground.playAround();
    }


    /**
     *  Initialized playground table (if needed) and works through the
     *  different showcases. All showcases can be turned on/off via
     *  corresponding <code>PlaygroundToggle</code>
     *
     *  <ul>
     *      <li>list tables</li>
     *      <li>scan table for items</li>
     *      <li>scan table for items with condition</li>
     *      <li>query table for items</li>
     *      <li>update items</li>
     *      <li>delete items</li>
     *      <li>delete table</li>
     *  </ul>
     */
    private void playAround() {

        System.out.println("===========================================");
        System.out.println("Init DynamoDB Playground");
        System.out.println("===========================================");


        // initialize playground
        init();

        System.out.println("\n===========================================");
        System.out.println("Playground Actions");
        System.out.println("===========================================");

        try {

            // list all tables of account (sync & blocking)
            if (LIST_TABLE.on()) {

                int limit = 5;
                listTables(limit);
            }

            // scan items
            if (SCAN_ITEMS.on()) {

                scanObjects();
                scanObjectsWhereFileNameStartsWith("berlin");
            }

            // insert item
            if (INSERT_ITEM.on()) {

                Image image = PLAYGROUND_IMAGE;
                insertItem(image);
            }


            // update item
            if (UPDATE_ITEM.on()) {

                Image image = PLAYGROUND_IMAGE;
                String updateField = COL_TITLE;
                String updateValue = image.getTitle() + "(UPDATED: " + new Date().toString() +")";
                updateObject(image.getUuid(), image.getName(), updateField, updateValue);
            }

            // query items
            if (QUERY_ITEMS.on()) {
                Image image = PLAYGROUND_IMAGE;
                queryObjects(image.getUuid());
            }

            // delete item
            if (DELETE_ITEM.on()){
                Image image = PLAYGROUND_IMAGE;
                deleteItem(image.getUuid(), image.getName());
            }

            // delete table
            if (DELETE_TABLE.on()) {

                String tableName = TABLE_NAME;
                deleteTable(tableName);

            }

        }  catch (AmazonServiceException ase) {
            displayAmazonServiceException(ase);
        } catch (AmazonClientException ace) {
            displayAmazonClientException(ace);
        }
    }


    /**
     * List all existing tables of referenced aws account in
     * a blocking and synchronous way.
     *
     * @param limit limits the tables to list
     */
    private void listTables(int limit) {

        System.out.println("\nLIST TABLES (sync)");
        System.out.println("===========================================\n");
        System.out.println("  REQUEST: ");
        System.out.println("  - limit: " + limit);

         /*
          * TODO list all tables of current account
          *  - use dynamoDB's listTables (...) method to list all tables
          *  - log/print out table names
          */

    }


    /**
     * Inserts image item into image table.
     *
     * @param image items to insert.
     */
    private void insertItem(Image image) {

        System.out.println("\nINSERT OBJECT (image)");
        System.out.println("===========================================\n");
        System.out.println("  REQUEST: ");
        System.out.println("  - image to store: " + image.getName());

        /*
         * TODO insert image into image table
         *  - use dynamoDB's putItem(...) method to insert item into table
         *  - log/print out corresponding response
         *
         *  TIP: use helper method imageToItem(...) to transform image to dynamoDB item
         */

    }


    /**
     * Scans image table for image items. Because of missing
     * condition all image items will be found.
     */
    private void scanObjects() {

        System.out.println("\nSCAN OBJECTS (all)");
        System.out.println("===========================================\n");
        System.out.println("  REQUEST: ");
        System.out.println("  - scan: find all ");

        /*
         * TODO scan objects to find all images
         *  - use dynamoDB's scan(...) method to scan table
         *  - log/print out list of images
         *
         *  TIP: use helper method printOut(Item) to log/print out single item
         */
    }

     /**
     * Scans image table for image items that satisfy a given condition
     * Condition: table field "fileName" starts with "value"
     * Filter expression: begins_with ( fileName, :val)
     *
     * @param value value to check against
     */
    private void scanObjectsWhereFileNameStartsWith(String value) {

        System.out.println("\nSCAN OBJECTS (with condition)");
        System.out.println("===========================================\n");
        System.out.println("  REQUEST: ");
        System.out.println("  - scan: find all where 'fileName' starts with '" + value + "'");

        /*
         * TODO scan objects to find images matching filter expression
         *  - use dynamoDB's scan(...) method to scan table
         *    - set filter expression to request
         *    - set expression attribute values to request
         *  - log/print out list of images
         *
         *  TIP: use helper method printOut(Item) to log/print out single item
         */

        // TODO set filter expression
        String filterExpression = null;

        // TODO set attribute value of type string for filter expression key (:val)
        Map<String, AttributeValue> expressionAttributeValues = null;

    }


    /**
     * Updates a specific table item (combined key = [id/name]).
     *
     * @param id specific id of the item to change
     * @param name name of the item to change
     * @param updateField table field to update
     * @param updateValue value to use for update
     */
    private void updateObject(String id, String name, String updateField, String updateValue) {

        System.out.println("\nUPDATE OBJECT ");
        System.out.println("===========================================\n");
        System.out.println("  REQUEST: ");
        System.out.println("  - key.id: " + id);
        System.out.println("  - key.fileName: " + name);
        System.out.println("  - update field : " + updateField);
        System.out.println("  - update value : " + updateValue);

        /*
         * TODO update single item in table
         *  - use dynamoDB's updateItem(...) method to update item
         *    - build "key" description map with the help of
         *      - KEY_COL_ID and id value of type string
         *      - KEY_COL_NAME and fileName of type string
         *    - build update expression with attributes
         *       - build update expression with 'SET " + updateField + " = :val'
         *       - build expressionAttributeValues" for ":val" with updateValue
         *    - set return value to ReturnValue.ALL_NEW to see updated values
         *  - log/print updated item
         *
         *  TIP: use helper method printOut(Item) to log/print out single item
         */


        // TODO define item lookup key
        //    Hash-key of the target item is string value $id
        //    Range-key of the target item is string value $fileName
        Map<String, AttributeValue> key = new HashMap<>();

        // TODO define update expression and update expression attributes
        String updateExpression = "";
        Map<String, AttributeValue> expressionAttributeValues = new HashMap<String, AttributeValue>();

        // TODO set expected return values
        ReturnValue returnValues = null;


    }


    /**
     * Queries table for a specific item, given by it's id.
     *
     * @param id unique id to query for.
     */
    private void queryObjects(String id) {

        System.out.println("\nQUERY OBJECTS (via key field)");
        System.out.println("===========================================\n");
        System.out.println("  REQUEST: ");
        System.out.println("  - key.id: " + id);

        /*
         * TODO query table for item with given id
         *  - use dynamoDB's query(...) method to query item
         *    - use key condition expression "id := val"
         *    - set expression attribute value with current id for key :val
         *  - log/print out found item(s)
         *
         *  TIP: use helper method printOut(Item) to log/print out single item
         */

        Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
    }

    /**
     * Deletes table item with a given key ([id/name]).
     *
     * @param id id part of the combined key
     * @param name name part of the combined key
     */
    private void deleteItem(String id, String name) {

        System.out.println("\nDELETE ITEM ");
        System.out.println("===========================================\n");
        System.out.println("  REQUEST: ");
        System.out.println("  - key.id: " + id);
        System.out.println("  - key.fileName: " + name);

        /*
         * TODO delete single item in table
         *  - use dynamoDB's deleteItem(...) method to delete item
         *    - build "key" description map with the help of
         *      - KEY_COL_ID and id value of type string
         *      - KEY_COL_NAME and fileName of type string
         *  - log/print out corresponding response
         */

        // Define item lookup key
        //    Hash-key of the target item is string value $id
        //    Range-key of the target item is string value $fileName
        Map<String, AttributeValue> key = new HashMap<>();

    }


    /**
     * Deletes a table.
     *
     * @param tableName name of table to delete
     */
    private void deleteTable(String tableName) {

        System.out.println("  REQUEST: ");
        System.out.println("  - table name: " + tableName);

        /*
         * TODO delete table
         *  - use dynamoDB's deleteTable(...) method to delete table
         *  - log/print deleted table name and status with the help of the corresponding response
         */
    }


    //----------  internal helper methods ------------------


    /**
     * Helper method to log aws specific exception of type
     * <code>AmazonServiceException</code>
     *
     * @param ex
     */
    private static void displayAmazonServiceException(AmazonServiceException ex) {
        System.out.println("Caught an AmazonServiceException, which means your request made it "
                + "to Amazon DynamoDB, but was rejected with an error response for some reason.");
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
                + "a serious internal problem while trying to communicate with DynamoDB, "
                + "such as not being able to access the network.");
        System.out.println("Error Message: " + ex.getMessage());
    }

    /**
     *
     * Helper method to transform an image domain object
     * into <code>Map<String, AttributeValue></code>.
     *
     * @param image image to transform
     * @return created map
     */
    private static Map<String, AttributeValue> imageToItem(Image image) {
        Map<String, AttributeValue > itemMap = new HashMap<>();

        AttributeValue.builder().s(image.getUuid()).build();
        itemMap.put(KEY_COL_ID, AttributeValue.builder().s(image.getUuid()).build());
        itemMap.put(KEY_COL_NAME, AttributeValue.builder().s(image.getName()).build());
        itemMap.put(COL_TITLE, AttributeValue.builder().s(image.getTitle()).build());
        itemMap.put(COL_DESCRIPTION, AttributeValue.builder().s(image.getDescription()).build());
        itemMap.put(COL_URI, AttributeValue.builder().s(image.getUri()).build());
        return itemMap;
    }

    /**
     * helper method to log values of db image item
     *
     * @param item image item to log
     */
    private void printOut(Map<String, AttributeValue> item) {
        System.out.println("    - key.id: " + item.get(KEY_COL_ID));
        System.out.println("    - key.fileName: " + item.get(KEY_COL_NAME));
        System.out.println("      - title: " + item.get(COL_TITLE));
        System.out.println("      - description: " + item.get(COL_DESCRIPTION));
        System.out.println("      - uri: " + item.get(COL_URI));
    }


    /**
     * Initializes playground.
     *
     * <ul>
     *     <li>creates table, if table is not already existing</li>
     *     <li>empties table, if table is already existing</li>
     *     <li>inserts initial image items</li>
     * </ul>
     */
    private void init() {

        dynamoDBClient = AwsClientProvider.getDynamoDBClient();
        imageRepository = new ImageRepository();

        try {
            DescribeTableRequest describeTableRequest =
                    DescribeTableRequest
                            .builder()
                            .tableName(TABLE_NAME)
                            .build();
            dynamoDBClient.describeTable(describeTableRequest);

            // table exists so delete existing data before filling
            ScanRequest scanRequest = ScanRequest.builder().tableName(TABLE_NAME).build();
            ScanResponse scanResponse = dynamoDBClient.scan(scanRequest);

            for (Map<String, AttributeValue> item : scanResponse.items()){
                String id = item.get(KEY_COL_ID).s();
                String name = item.get(KEY_COL_NAME).s();

                deleteItem(id, name);
            }

        } catch (ResourceNotFoundException ex) {

            // table does not exist and must be created
            createTable(
                    TABLE_NAME, // table name
                    5L, // readCapacityUnits
                    5L, // writeCapacityUnits
                    KEY_COL_ID, // partitionKeyName
                    "S", //partitionKeyType
                    KEY_COL_NAME, // sortKeyName
                    "S"); // sortKeyType
        }

        // insert some items into table
        insertItems(imageRepository.findAll());
    }


    /**
     * Common helper to create a dynamoDB table.
     *
     * @param tableName name of table to create
     * @param readCapacityUnits requested read capacity
     * @param writeCapacityUnits requested write capacity
     * @param partitionKeyName name of the partition key
     * @param partitionKeyType type of the partition key
     * @param sortKeyName name of the optional sort key
     * @param sortKeyType type of the optional sort key
     */
    private void createTable(String tableName, long readCapacityUnits, long writeCapacityUnits,
                             String partitionKeyName, String partitionKeyType, String sortKeyName, String sortKeyType ) {

        System.out.println("\nCREATE TABLE IF NOT EXIST");
        System.out.println("===========================================\n");

        System.out.println("  REQUEST: ");
        System.out.println("  - table name: " + tableName);
        System.out.println("  - read capacity: " + readCapacityUnits);
        System.out.println("  - write capacity: " + writeCapacityUnits);
        System.out.println("  - partition key: " + partitionKeyName + " / " + partitionKeyType );
        System.out.println("  - sort key: " + sortKeyName + " / " + sortKeyType );


        // 1. build partition key and additional sort key
        ArrayList<KeySchemaElement> keySchema = new ArrayList<KeySchemaElement>();
        ArrayList<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();

        // 1.1 partition key "uuid"
        keySchema.add(KeySchemaElement.builder().attributeName(partitionKeyName).keyType(KeyType.HASH).build());
        attributeDefinitions.add(AttributeDefinition.builder().attributeName(partitionKeyName).attributeType(partitionKeyType).build());

        // 1.1 additional sort key "name"
        if (sortKeyName != null) {
            System.out.println("  - sort key: " + sortKeyName + " / " + sortKeyType );
            keySchema.add(KeySchemaElement.builder().attributeName(sortKeyName).keyType(KeyType.RANGE).build());
            attributeDefinitions.add(AttributeDefinition.builder().attributeName(sortKeyName).attributeType(sortKeyType).build());
        }

        // 2. build ProvisionedThroughput
        ProvisionedThroughput provisionedThroughput = ProvisionedThroughput.builder().readCapacityUnits(readCapacityUnits).writeCapacityUnits(writeCapacityUnits).build();

        // 3. build CreateTableRequest
        CreateTableRequest createTableRequest =
                CreateTableRequest
                        .builder()
                        .tableName(tableName)
                        .provisionedThroughput(provisionedThroughput)
                        .keySchema(keySchema)
                        .attributeDefinitions(attributeDefinitions)
                        .build();

        CreateTableResponse createTableResponse = dynamoDBClient.createTable(createTableRequest);
        TableDescription tableDescription = createTableResponse.tableDescription();

        System.out.println("  RESPONSE: ");
        System.out.println("  - : " + tableDescription.tableName());
        System.out.println("  - : " + tableDescription.tableArn());
        System.out.println("  - : " + tableDescription.tableStatus());
        System.out.println("  - : " + tableDescription.creationDateTime());

        System.out.print("  WAIT UNTIL TABLE IS ACTIVE ... ");
        try {
            TableUtils.waitUntilActive(dynamoDBClient, tableName);
            System.out.println("DONE");
        } catch (InterruptedException ex) {
            System.out.println("PROBLEM OCCURRED " + ex.getMessage());
        }
    }

    /**
     * Inserts image items into image table.
     *
     * @param images items to insert.
     */
    private void insertItems(List<Image> images) {

        System.out.println("\nINSERT OBJECT (image)");
        System.out.println("===========================================\n");
        System.out.println("  REQUEST: ");
        System.out.println("  - images to store: " + images.size());

        // insert images
        for (Image image: images) {

            insertItem(image);

            System.out.println("  -   store images: " + image.getName());
            PutItemRequest putItemRequest =
                    PutItemRequest
                            .builder()
                            .tableName(TABLE_NAME)
                            .item(imageToItem(image))
                            .build();

            PutItemResponse putItemResponse = dynamoDBClient.putItem(putItemRequest);
            System.out.println("  -   image stored: " + putItemResponse.toString());
        }

        System.out.println("  RESPONSE: ");
        System.out.println("    - images stored: " + images.size());
    }


}
