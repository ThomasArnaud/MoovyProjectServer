package com.moovy.server.services;

import com.moovy.server.model.Category;
import com.moovy.server.repository.CategoryRepository;
import com.moovy.server.repository.MovieRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;


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
     * Produces a list of categories that can be filtered through a "query" parameter.
     *
     * @return The list of categories.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getList(@QueryParam("query") String query) {

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
     * Produces a single category, reffered using the code of the category.
     *
     * @return The movie.
     */
    @GET
    @Path("/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategory(@PathParam("code") String code) {
        Category category = new CategoryRepository().fetch(code);

        if (category != null) {
            return Response
                    .ok(category)
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
    public Response updateCategory(Category category)
    {

        new CategoryRepository().save(category);

        return Response
                .ok()
                .build()
        ;
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
    public Response deleteCategory(@PathParam("code") String code)
    {

        CategoryRepository repository = new CategoryRepository();

        Category category = repository.fetch(code);

        if(category != null) {

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
