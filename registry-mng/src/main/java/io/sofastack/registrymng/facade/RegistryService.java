package io.sofastack.registrymng.facade;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @author FUDIAN
 * @date 2020-12-25
 */
@Path("registry")
//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
//@Produces(MediaType.APPLICATION_FORM_URLENCODED)
public interface RegistryService {
    @GET
    @Path("hello/{name}")
    String hello(@PathParam("name") String name);
}
