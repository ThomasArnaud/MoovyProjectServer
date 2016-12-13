package com.moovy.server.services;

import com.moovy.server.model.Category;
import com.moovy.server.repository.CategoryRepository;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;


/**
 * @author Thomas Arnaud (thomas.arnaud@etu.univ-lyon1.fr)
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 * @author Alexis Rabilloud (alexis.rabilloud@etu.univ-lyon1.fr)
 */

@Path("/categories")
public class CategoriesWebservice
{

    /**
     *
     */
    @Context
    UriInfo uriInfo;

    /**
     * Produces a single category, reffered using the code of the category.
     *
     * @return The movie.
     */
    @GET
    @Path("/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOne(@PathParam("code") String code)
    {
        Category category = new CategoryRepository().fetch(code);

        if (category != null)
        {
            return Response
                .ok(category)
                .build()
            ;
        }
        else
        {
            return Response
                .status(Response.Status.NOT_FOUND)
                .build()
            ;
        }
    }

    /**
     * Produces a list of categories that can be filtered through a "query" parameter.
     *
     * @return The list of categories.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getList(@QueryParam("query") String query)
    {
        // Initialize vars
        CategoryRepository repository = new CategoryRepository();

        if(query != null && !query.trim().isEmpty())
        {
            return Response
                .ok(repository.lookup(query))
                .build()
            ;
        }
        else
        {
            return Response
                .ok(repository.fetchAll())
                .build()
            ;
        }
    }

    /**
     * Adds a new category to the database.
     *
     * @return A success or failure response.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Category category)
    {
        new CategoryRepository().save(category);

        UriBuilder uriBuilder = this.uriInfo.getAbsolutePathBuilder();
        uriBuilder.path("/categories/" + category.getCode());

        return Response
            .created(uriBuilder.build())
            .build()
        ;
    }

    /**
     * Updates a category from the database.
     *
     * @return A success or failure response.
     */
    @PUT
    @Path("/{code}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Category category)
    {
        // Initialize vars
        CategoryRepository repository = new CategoryRepository();

        if(repository.fetch(category.getCode()) != null)
        {
            // Update the category
            repository.save(category);

            // Build response
            return Response
                .ok()
                .build()
            ;
        }
        else
        {
            return Response
                .status(Response.Status.BAD_REQUEST)
                .build()
            ;
        }
    }

    /**
     * Deletes a category from the database.
     *
     * @param code The category's code.
     * @return A success or failure response.
     */
    @DELETE
    @Path("/{code}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("code") String code)
    {
        // Initialize vars
        CategoryRepository repository = new CategoryRepository();

        // Does the category exist?
        Category category = repository.fetch(code);

        if(category != null)
        {
            repository.delete(category);

            return Response
                .noContent()
                .build()
            ;
        }
        else {
            return Response
                .status(Response.Status.NOT_FOUND)
                .build()
            ;
        }
    }
}
