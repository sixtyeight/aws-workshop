package de.openknowledge.sample.cloud.aws.paas.dynamodb.common;

public enum PlaygroundToggle {

    ON(true), OFF(false);

    private final boolean value;

    PlaygroundToggle(boolean value) {
        this.value = value;
    }
    
    public boolean on() {
        return this.value;
    }


}
