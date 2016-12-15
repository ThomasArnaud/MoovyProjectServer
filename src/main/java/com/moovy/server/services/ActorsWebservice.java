package com.moovy.server.services;

import com.moovy.server.model.Actor;
import com.moovy.server.repository.ActorRepository;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 * @author Thomas Arnaud (thomas.arnaud@etu.univ-lyon1.fr)
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 * @author Alexis Rabilloud (alexis.rabilloud@etu.univ-lyon1.fr)
 */
@Path("/actors")
public class ActorsWebservice
{
    /**
     *
     */
    @Context
    UriInfo uriInfo;

    /**
     * Produces a single actor.
     *
     * @return The actor.
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOne(@PathParam("id") int id)
    {
        Actor actor = new ActorRepository().fetch(id);

        if(actor != null)
        {
            return Response
                .ok(actor)
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
     * Produces a list of actors that can be filtered through a "query" parameter.
     *
     * @return The list of actors.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getList(@QueryParam("query") String query)
    {
        // Initialize vars
        ActorRepository repository = new ActorRepository();

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
     * Adds a new actor to the database.
     *
     * @return A success or failure response.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Actor actor)
    {
        // Save the new actor
        new ActorRepository().save(actor);

        // Build response
        UriBuilder uriBuilder = this.uriInfo.getAbsolutePathBuilder();
        uriBuilder.path("/actors/" + actor.getId());

        return Response
            .created(uriBuilder.build())
            .build()
        ;
    }

    /**
     * Updates an actor from the database.
     *
     * @return A success or failure response.
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Actor actor)
    {
        // Initialize vars
        ActorRepository repository = new ActorRepository();

        if(repository.exists(actor.getId()))
        {
            // Update the actor
            repository.save(actor);

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
     * Deletes an actor from the database.
     *
     * @param id The actor's id.
     * @return A success or failure response.
     */
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id)
    {
        // Initialize vars
        ActorRepository repository = new ActorRepository();

        // Does the actor exist?
        Actor actor = repository.fetch(id);

        if(actor != null)
        {
            // Delete the actor
            repository.delete(actor);

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
