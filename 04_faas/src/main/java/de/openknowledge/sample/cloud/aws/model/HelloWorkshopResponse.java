package de.openknowledge.sample.cloud.aws.model;

/**
 * "AWS" return object greeting a conference
 *
 *  JSON payload looks like:
 *
 *    {
 *         "greeting" : "Welcome to Super-Conference!"
 *    }
 *
 */
public class HelloWorkshopResponse {

    private String greeting;

    public HelloWorkshopResponse() {
        this("");
    }

    public HelloWorkshopResponse(String greeting) {
        this.greeting = greeting;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
}
