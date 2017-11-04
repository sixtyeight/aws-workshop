package de.openknowledge.sample.cloud.aws.domain;

import java.util.UUID;

public class Image {

    private String uuid;
    private String name;
    private String title;
    private String description;
    private String uri;

    public Image() {

    }

    public Image(String title, String description, String name, String uri) {
        this.uuid = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.name = name;
        this.uri = uri;
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
