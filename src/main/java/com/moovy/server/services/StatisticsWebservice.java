package com.moovy.server.services;

import com.moovy.server.repository.StatisticsRepository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * @author Thomas Arnaud (thomas.arnaud@etu.univ-lyon1.fr)
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 * @author Alexis Rabilloud (alexis.rabilloud@etu.univ-lyon1.fr)
 */

@Path("/statistics")
public class StatisticsWebservice
{
    /**
     * The context's URI info.
     */
    @Context
    UriInfo uriInfo;

    /**
     * Fetches the dashboard's statistics.
     *
     * @return Map containing associations between tables and number of entries
     */
    @GET
    @Path("/dashboard")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDashboard()
    {
        return Response
            .ok(new StatisticsRepository().getDashboard())
            .build()
        ;
    }
}
