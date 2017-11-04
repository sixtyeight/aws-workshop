package de.openknowledge.sample.cloud.aws.model;


/**
 * "AWS" input event to specify a conference
 *
 *  JSON payload looks like:
 *
 *    {
 *         "name" : "Super-Conference"
 *    }
 *
 */
public class HelloWorkshopRequest {

    public static String UNKNOWN_CONFERENCE = "UNKNOWN_CONFERENCE";

    private String name;

    public HelloWorkshopRequest() {
        name = UNKNOWN_CONFERENCE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) {
            name = UNKNOWN_CONFERENCE;
        }
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }

}
