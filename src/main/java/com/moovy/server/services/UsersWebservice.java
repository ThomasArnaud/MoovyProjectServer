package com.moovy.server.services;

import com.moovy.server.model.User;
import com.moovy.server.repository.UserRepository;
import com.moovy.server.utils.HashUtil;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.Map;

/**
 * @author Thomas Arnaud (thomas.arnaud@etu.univ-lyon1.fr)
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 * @author Alexis Rabilloud (alexis.rabilloud@etu.univ-lyon1.fr)
 */

@Path("/users")
public class UsersWebservice {

    @Context
    UriInfo uriInfo;

    /*
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOne(@PathParam("id") int id) {
        User user = new UserRepository().fetch(id);

        if (user != null)
        {
            return Response
                    .ok(user)
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
    */

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginUser(Map<String, String> logins) {
        UserRepository repository = new UserRepository();

        User user =  repository.fetchMail(logins.get("email"));

        if (user == null)
        {
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .build()
            ;
        }

        String password = logins.get("password");

        if (user.getPassword().equals(HashUtil.sha256("moovy" + password))) {
            return Response
                    .ok()
                    .build()
            ;
        }

        return Response
                .status(Response.Status.UNAUTHORIZED)
                .build()
        ;
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerUser(User user) {

        UserRepository repository = new UserRepository();

        user.setPassword("moovy"+user.getPassword());

        UriBuilder uriBuilder = this.uriInfo.getAbsolutePathBuilder();
        uriBuilder.path("/users" + user.getId());

        return Response
                .created(uriBuilder.build())
                .build()
        ;
    }

}
