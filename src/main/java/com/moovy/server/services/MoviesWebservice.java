package com.moovy.server.services;

import javax.ws.rs.*;

/**
 * @author Thomas Arnaud (thomas.arnaud@etu.univ-lyon1.fr)
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 * @author Alexis Rabilloud (alexis.rabilloud@etu.univ-lyon1.fr)
 */
@Path("/movies")
public class MoviesWebservice
{
    /**
     *
     * @return
     */
    @GET
    @Path("/")
    @Produces("application/json")
    public String moviesList()
    {
        return "Test";
    }

    /**
     *
     * @return
     */
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public String movie(@PathParam("id") int id) { return "OneMovies"; }

    /**
     *
     * @param query
     * @return
     */
    @GET
    @Path("?query={string}")
    @Produces("application/json")
    public String searchMovie(@PathParam("string") String query) {return "search results";}

    /**
     *
     *
     */
    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    public void updateMovie(@PathParam("id") int id) { }

    /**
     *
     *
     */
    @POST
    @Path("/")
    @Consumes("application/json")
    public void addMovie() { }

    /**
     *
     * @param id
     */
    @DELETE
    @Path("/{id}")
    @Consumes("application/json")
    public void deleteMovie(@PathParam("id") int id) {}
}
