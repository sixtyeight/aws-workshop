package de.openknowledge.sample.cloud.aws.paas.dynamodb.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImageRepository {

    private Map<String, Image> imageMap;

    public ImageRepository() {
        init();
    }

    public List<Image> findAll() {
        return new ArrayList(imageMap.values());
    }

    public Image findById(String id) throws ImageNotFoundException {
        if (imageMap.containsKey(id)) {
            return imageMap.get(id);
        } else {
            throw new ImageNotFoundException(id);
        }
    }

    public void create(Image image) {
        imageMap.put(image.getUuid(), image);
    }

    public void update(Image image) throws ImageNotFoundException {
        if (imageMap.containsKey(image.getUuid())) {
            imageMap.put(image.getUuid(), image);
        } else {
            throw new ImageNotFoundException(image.getUuid());
        }
    }

    public void delete(String id) throws ImageNotFoundException {
        if (imageMap.containsKey(id)) {
            imageMap.remove(id);
        } else {
            throw new ImageNotFoundException(id);
        }
    }

    private void init() {
        imageMap = new HashMap<>();
        // create images
        Image image01 = new Image("286f1d3b-6871-4cf7-a6ac-cc68c02a30e7","Sacre-Couer", "Sacre Couer de Montmatre", "paris01.jpg");
        Image image02 = new Image("9b2d5d91-29ac-4382-b64d-c31a6fbd1c9e", "Louvre", "Louvre bei Nacht", "paris02.jpg");
        Image image03 = new Image("f6053cb7-c0ab-4f4a-bb47-e934cfabcd34", "Musee George Pompidou", "Museum in der Aussenansicht", "paris03.jpg");

        Image image04 = new Image("bc3e31b7-021b-4f32-8aea-4431a18538e5","East-Side", "Graffiti Kunst an der East-Side Gallery", "berlin01.jpg");
        Image image05 = new Image("c23fa9ba-b26d-4937-b692-ca20259260a0", "Fernsehturm", "Berliner Fernsehturm bei bestem Wetter", "berlin02.jpg");
        Image image06 = new Image("4df8a9f4-7906-472b-8721-4f54b6ba2b4a", "Kanzleramt", "Kanzleramt in der Totale", "berlin03.jpg");

        create(image01);
        create(image02);
        create(image03);
        create(image04);
        create(image05);
        create(image06);
    }


}
