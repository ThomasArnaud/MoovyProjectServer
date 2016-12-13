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

    @Context
    UriInfo uriInfo;

    /**
     *
     * @return
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
     *
     * @param id
     * @return
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategory(@PathParam("id") int id) {
        Category category = new CategoryRepository().fetch(id);

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
     *
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
     *
     * @param category
     */
    @PUT
    @Path("/{id}")
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
     *
     * @param id
     */
    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteCategory(@PathParam("id") int id)
    {

        CategoryRepository repository = new CategoryRepository();

        Category category = repository.fetch(id);

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
