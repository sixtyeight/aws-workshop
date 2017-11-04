package de.openknowledge.sample.cloud.aws.paas.dynamodb.domain;

import static org.apache.commons.lang3.Validate.notNull;

/**
 * Exception to be thrown when an image is not found.
 */
public class ImageNotFoundException extends Exception {

    private final String imageId;

    /**
     * @param imageId the given image identifier.
     * @throws NullPointerException
     */
    public ImageNotFoundException(final String imageId) {
        super();
        this.imageId = notNull(imageId, "imageId must not be null");
    }

    /**
     * @param imageId the given image identifier.
     * @param cause        the cause
     * @throws NullPointerException
     */
    public ImageNotFoundException(final String imageId, final Throwable cause) {
        super(cause);
        this.imageId = notNull(imageId, "imageId must not be null");
    }

    public String getImage() {
        return imageId;
    }
}
