package com.moovy.server.services;

import com.moovy.server.model.Director;
import com.moovy.server.repository.DirectorRepository;

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
@Path("/directors")
public class DirectorsWebservice
{
    /**
     *
     */
    @Context
    UriInfo uriInfo;

    /**
     * Produces a single director.
     *
     * @return The director.
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOne(@PathParam("id") int id)
    {
        Director director = new DirectorRepository().fetch(id);

        if(director != null)
        {
            return Response
                .ok(director)
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
     * Produces a list of directors that can be filtered through a "query" parameter.
     *
     * @return The list of directors.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getList(@QueryParam("query") String query)
    {
        // Initialize vars
        DirectorRepository repository = new DirectorRepository();

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
     * Adds a new director to the database.
     *
     * @return A success or failure response.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Director director)
    {
        // Save the new director
        new DirectorRepository().save(director);

        // Build response
        UriBuilder uriBuilder = this.uriInfo.getAbsolutePathBuilder();
        uriBuilder.path("/directors/" + director.getId());

        return Response
            .created(uriBuilder.build())
            .build()
        ;
    }

    /**
     * Updates a director from the database.
     *
     * @return A success or failure response.
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Director director)
    {
        // Initialize vars
        DirectorRepository repository = new DirectorRepository();

        if(repository.fetch(director.getId()) != null)
        {
            // Update the director
            repository.save(director);

            // Build response
            return Response
                .ok()
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
     * Deletes a director from the database.
     *
     * @param id The director's id.
     * @return A success or failure response.
     */
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id)
    {
        // Initialize vars
        DirectorRepository repository = new DirectorRepository();

        // Does the director exist?
        Director director = repository.fetch(id);

        if(director != null)
        {
            // Delete the director
            repository.delete(director);

            // Build response
            return Response
                .noContent()
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

}
