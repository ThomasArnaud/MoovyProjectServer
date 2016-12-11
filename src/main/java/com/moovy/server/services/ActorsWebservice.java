package com.moovy.server.services;

import com.moovy.server.model.Actor;
import com.moovy.server.repository.ActorRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Thomas Arnaud (thomas.arnaud@etu.univ-lyon1.fr)
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 * @author Alexis Rabilloud (alexis.rabilloud@etu.univ-lyon1.fr)
 */
@Path("/actors")
public class ActorsWebservice {

    @GET
    @Path("/getActor/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Actor getOne(int id)
    {
        return new ActorRepository().getActorById(id);
    }

    @GET
    @Path("/getAllActors")
    @Produces(MediaType.APPLICATION_JSON)
    public List getAll()
    {
        return new ArrayList();
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
