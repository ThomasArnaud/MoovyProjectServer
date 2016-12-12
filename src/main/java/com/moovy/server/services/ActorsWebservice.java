package com.moovy.server.services;

import com.moovy.server.model.Actor;
import com.moovy.server.repository.ActorRepository;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Thomas Arnaud (thomas.arnaud@etu.univ-lyon1.fr)
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 * @author Alexis Rabilloud (alexis.rabilloud@etu.univ-lyon1.fr)
 */
@Path("/actors")
public class ActorsWebservice
{
    @GET
    @Path("/getActor/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Actor getOne(@PathParam("id") int id)
    {
        return new ActorRepository().fetch(id);
    }

    @GET
    @Path("/getAllActors")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Actor> getAll()
    {
        return new ArrayList<>();
    }

    @POST
    @Path("/add")
    @Consumes("application/json")
    public int add()
    {
        return 0;
    }

    @PUT
    @Path("/update")
    @Consumes("application/json")
    public int update(int id)
    {
        return 0;
    }

    @DELETE
    @Path("/getAll")
    @Consumes("application/json")
    public int delete()
    {
        return 0;
    }

}
