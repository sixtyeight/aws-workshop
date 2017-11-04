package de.openknowledge.sample.cloud.aws.paas.dynamodb.domain;

import java.util.UUID;

public class Image {

    static final String BASE_URI = "http://localhost:8080/iaas-demo/upload/images/";

    private String uuid;
    private String name;
    private String title;
    private String description;
    private String uri;


    public Image(String title, String description, String name) {
        this(UUID.randomUUID().toString(), title, description, name);
    }

    public Image(String id, String title, String description, String name) {
        this.uuid = id;
        this.title = title;
        this.description = description;
        this.name = name;
        this.uri = BASE_URI + name;
    }

    public String getName() {
        return name;
    }

    public String getUuid() {
        return uuid;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUri() {
        return uri;
    }
}
