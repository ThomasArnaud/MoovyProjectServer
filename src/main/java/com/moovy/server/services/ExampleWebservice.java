package com.moovy.server.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * @author Thomas Arnaud (thomas.arnaud@etu.univ-lyon1.fr)
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 * @author Alexis Rabilloud (alexis.rabilloud@etu.univ-lyon1.fr)
 */
@Path("/examples")
public class ExampleWebservice
{
    /**
     * An exemple of webservice.
     *
     * @return An empty JSON array.
     */
    @GET
    // @Path("/")
    @Produces("application/json")
    public String examplesList()
    {
        return "[]";
    }
}
