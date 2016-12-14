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
public class UsersWebservice
{
    @Context
    UriInfo uriInfo;

    /**
     *
     * @param loginData The user's credentials.
     * @return The user if the credentials are correct, an error otherwise.
     */
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginUser(Map<String, String> loginData)
    {
        // Initialize vars
        UserRepository repository = new UserRepository();

        // Does the user exist?
        if(!loginData.containsKey("email"))
        {
            return Response
                .status(Response.Status.BAD_REQUEST)
                .build()
            ;
        }

        User user =  repository.fetchByEmail(loginData.get("email"));

        if(user == null)
        {
            return Response
                .status(Response.Status.UNAUTHORIZED)
                .build()
            ;
        }

        // Is the password valid?
        if(!loginData.containsKey("password"))
        {
            return Response
                .status(Response.Status.BAD_REQUEST)
                .build()
            ;
        }

        if(user.getPassword().equals(HashUtil.sha256("Moovy" + loginData.get("password"))))
        {
            return Response
                .ok(user)
                .build()
            ;
        }

        return Response
            .status(Response.Status.UNAUTHORIZED)
            .build()
        ;
    }

    /**
     *
     * @param user
     * @return
     */
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerUser(User user)
    {
        // Hash password
        user.setPassword(HashUtil.sha256("Moovy" + user.getPassword()));

        // Then, save user
        new UserRepository().save(user);

        return Response
            .ok()
            .build()
        ;
    }

}
