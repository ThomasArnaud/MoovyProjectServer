package com.moovy.server.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

/**
 * @author Thomas Arnaud (thomas.arnaud@etu.univ-lyon1.fr)
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 * @author Alexis Rabilloud (alexis.rabilloud@etu.univ-lyon1.fr)
 */
@Path("/examples")
public class ExampleWebservice
{
    /**
     *
     */
    @Context
    UriInfo uriInfo;

    /**
     * An exemple of webservice.
     *
     * @return An empty JSON array.
     */
    @GET
    // @Path("/")
    @Produces("application/json")
    public Response examplesList()
    {
        UriBuilder uriBuilder = this.uriInfo.getAbsolutePathBuilder();
        uriBuilder.path("/movies/" + 1);

        return
            Response
            .created(uriBuilder.build())
            .build()
        ;
    }
}
