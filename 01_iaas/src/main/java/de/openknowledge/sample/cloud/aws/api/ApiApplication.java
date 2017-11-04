package de.openknowledge.sample.cloud.aws.api;

import org.glassfish.jersey.CommonProperties;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.*;

@ApplicationPath("api")
public class ApiApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {

        Set<Class<?>> resources = new HashSet<Class<?>>(Arrays.asList(ImagesResource.class));
        return resources;

    }

    @Override
    public Map<String, Object> getProperties() {
        HashMap<String, Object> properties = new HashMap<>();

        properties.put(CommonProperties.MOXY_JSON_FEATURE_DISABLE, true);

        return properties;
    }
}
