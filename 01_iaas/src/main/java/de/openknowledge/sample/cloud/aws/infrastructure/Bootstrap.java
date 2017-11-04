package de.openknowledge.sample.cloud.aws.infrastructure;

import de.openknowledge.sample.cloud.aws.domain.Image;
import de.openknowledge.sample.cloud.aws.domain.ImageRepository;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.List;

/**
 * Demo data loader to create an initial order.
 */
@Singleton
@Startup
public class Bootstrap {

    // TODO: replace with real context info,
    // static final String BASE_URI = "http://HOST:PORT/aws_iaas-excercises/upload/images/";
    static final String BASE_URI = "/upload/images/";

    @Inject
    private ImageRepository imageRepository;

    @PostConstruct
    protected void init() {

        // create images
        Image image01 = new Image("Sacre-Couer", "Sacre Couer de Montmatre", "paris01.jpg", BASE_URI + "paris01.jpg");
        Image image02 = new Image("Louvre", "Louvre bei Nacht", "paris02.jpg", BASE_URI + "paris02.jpg");
        Image image03 = new Image("Musee George Pompidou", "Museum in der Aussenansicht", "paris03.jpg", BASE_URI + "paris03.jpg");

        Image image04 = new Image("East-Side", "Graffiti Kunst an der East-Side Gallery", "berlin01.jpg", BASE_URI + "berlin01.jpg");
        Image image05 = new Image("Fernsehturm", "Berliner Fernsehturm bei bestem Wetter", "berlin02.jpg", BASE_URI + "berlin02.jpg");
        Image image06 = new Image("Kanzleramt", "Kanzleramt in der Totale", "berlin03.jpg", BASE_URI + "berlin03.jpg");

        imageRepository.create(image01);
        imageRepository.create(image02);
        imageRepository.create(image03);
        imageRepository.create(image04);
        imageRepository.create(image05);
        imageRepository.create(image06);

        List<Image> images = imageRepository.findAll();
        for (Image image: images) {
            System.out.println("Image: " + image.getUuid());
        }
    }

}