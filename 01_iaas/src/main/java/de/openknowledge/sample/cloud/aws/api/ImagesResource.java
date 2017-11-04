package de.openknowledge.sample.cloud.aws.api;

import de.openknowledge.sample.cloud.aws.domain.Image;
import de.openknowledge.sample.cloud.aws.domain.ImageNotFoundException;
import de.openknowledge.sample.cloud.aws.domain.ImageRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("images")
public class ImagesResource {

    @Inject
    private ImageRepository repository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listImages() {

        List<Image> images = repository.findAll();
        return Response.ok().entity(images).build();
    }

    @GET
    @Path("{imageId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getImage(@PathParam("imageId") String imageId) {
        Image image = null;
        try {
            image = repository.findById(imageId);
        } catch (ImageNotFoundException ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(image).build();
    }

}
