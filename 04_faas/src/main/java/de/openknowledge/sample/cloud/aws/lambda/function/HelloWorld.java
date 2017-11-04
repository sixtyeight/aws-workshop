package de.openknowledge.sample.cloud.aws.lambda.function;


import com.amazonaws.services.lambda.runtime.Context;

public class HelloWorld {

    /**
     * Handles a request to the lambda function.
     *
     * @param conference Input parameter of the lambda function +
     * @return Welcome string, calculated in respect to the input parameter <code>conference</code>
     */
    public String handleLambdaRequest(String conference, Context context) {

        String lambdaReturn;

        // generate output with the help of the input parameter
        if (conference != null ) {
            lambdaReturn = "Welcome to " + conference + "!";
        } else {
            lambdaReturn = "WT#!? We are lost in space!";
        }
        return lambdaReturn;
    }
}
