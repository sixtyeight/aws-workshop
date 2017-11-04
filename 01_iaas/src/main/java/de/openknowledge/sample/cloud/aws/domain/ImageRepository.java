package de.openknowledge.sample.cloud.aws.domain;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class ImageRepository {

    private Map<String, Image> imageMap;

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

    @PostConstruct
    private void init() {
        imageMap = new HashMap<>();
    }


}
