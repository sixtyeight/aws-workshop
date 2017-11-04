package de.openknowledge.sample.cloud.aws.lambda.function;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

import java.util.Map;

/**
 * Lambda function - implementing the <code>RequestHandler</code> interface with
 * <code>HelloConferenceRequest</code> as input and <code>HelloConferenceResponse</code>
 * as output parameter - to say hello to a conference.
 *
 * If no input event of exists, the function should use an environment variable
 * <code>defaultConference</code> as default value.
 *
 * An additional environment variable <code>logContextInfo</code> indicates, if additional information
 * should be logged.
 *
 * TODO implement com.amazonaws.services.lambda.runtime.RequestHandler interface
 */
// de.openknowledge.sample.cloud.aws.lambda.function.HelloConference
public class HelloWorkshop {


    private final static String ENV_VAR_PRINT_CONTEXT_INFO = "logContextInfo";
    private final static String ENV_VAR_DEFAULT_CONFERENCE = "defaultConference";

    /**
     * Handles a request to the lambda function.
     *
     * @param conference Input parameter of the lambda function representing a conference
     * @param context Related context of the lambda function.
     * @return Welcome response, calculated in respect to the input parameter.
     *
     * TODO implement RequestHandler method:
     *  step 1: - check for env variables (understand and use helper methods)
     *  step 2: - retrieve lambda logger from context
     *  step 3: - create welcome response, calculated in respect to the input parameter
     *             - if input is "null" or "empty" calculate a different response
     *  step 4: - log additional context information, if related env variable is set
     *  step 5: - return response of type HelloConferenceResponse
     *
     *  PRO-TIP: log!
     *
     */


    //----------  internal helper methods ------------------


    /**
     * Logs context specific information with the help of
     * the context related <code>LogStream</code>.
     *
     * @param context
     */
    private void logContextInformation(Context context) {

        LambdaLogger logger = context.getLogger();
        logger.log("Function name: " + context.getFunctionName());
        logger.log("Max mem allocated: " + context.getMemoryLimitInMB());
        logger.log("Time remaining in milliseconds: " + context.getRemainingTimeInMillis());
        logger.log("CloudWatch log stream name: " + context.getLogStreamName());
        logger.log("CloudWatch log group name: " + context.getLogGroupName());
    }


    /**
     * Retrieves a String based value for a given
     * environment variable, given by its key.
     *
     * @param key name of the environment variable
     * @return String based value, if environment variable exists
     *         empty string, else
     */
    private String getEnvVarAsString(String key) {
        Map<String, String> environment = System.getenv();
        String value = environment.get(key);
        value = value != null? value : "";
        return value;
    }


    /**
     * Retrieves a Boolean based value for a given
     * environment variable, given by its key.
     *
     * @param key name of the environment variable
     * @return Boolean based value, if environment variable exists
     *         false, else
     */
    private Boolean getEnvVarAsBoolean(String key) {
        Map<String, String> environment = System.getenv();
        String value = environment.get(key);
        value = value != null? value : "false";
        return Boolean.valueOf(value);

    }


}