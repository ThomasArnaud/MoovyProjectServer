package com.moovy.server.services;

import javax.ws.rs.*;

/**
 * @author Thomas Arnaud (thomas.arnaud@etu.univ-lyon1.fr)
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 * @author Alexis Rabilloud (alexis.rabilloud@etu.univ-lyon1.fr)
 */

@Path("/categories")
public class CategoriesWebservice {

    /**
     *
     * @return
     */
    @GET
    @Path("/")
    @Produces("application/json")
    public String categoriesList() { return "Test"; }

    /**
     *
     * @param id
     * @return
     */
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public String getCategory(@PathParam("id") int id) { return "OneCaregory"; }

    /**
     *
     * @param query
     * @return
     */
    @GET
    @Path("?query={string}")
    @Produces("application/json")
    public String searchCategory(@PathParam("string") String query) { return "test"; }


    /**
     *
     * @param id
     */
    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    public void updateCategory(@PathParam("id") int id) {}


    /**
     *
     */
    @POST
    @Path("/")
    @Consumes("application/json")
    public void addCategory() {}


    /**
     *
     * @param id
     */
    @DELETE
    @Path("/{id}")
    @Consumes("application/json")
    public void deleteCtaegory(@PathParam("id") int id) {}



}
