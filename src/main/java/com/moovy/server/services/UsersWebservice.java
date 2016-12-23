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
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginUser(Map<String, String> loginData)
    {
        // Initialize vars
        UserRepository repository = new UserRepository();

        // Does the user exist?
        if(!loginData.containsKey("email"))
        {
            return Response
                .status(Response.Status.BAD_REQUEST)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build()
            ;
        }

        User user =  repository.fetchByEmail(loginData.get("email"));

        if(user == null)
        {
            return Response
                .status(Response.Status.UNAUTHORIZED)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build()
            ;
        }

        // Is the password valid?
        if(!loginData.containsKey("password"))
        {
            return Response
                .status(Response.Status.BAD_REQUEST)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build()
            ;
        }

        System.out.println(loginData);

        if(user.getPassword().equals(HashUtil.sha256("Moovy" + loginData.get("password"))))
        {
            return Response
                .ok(user)
                .build()
            ;
        }

        return Response
            .status(Response.Status.UNAUTHORIZED)
            .type(MediaType.APPLICATION_JSON_TYPE)
            .build()
        ;
    }

    /**
     *
     * @param user
     * @return
     */
    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerUser(User user)
    {
        // Initialize vars
        UserRepository repository = new UserRepository();

        if(!repository.exists(user.getEmail()))
        {
            // Hash password with salt
            user.setPassword(HashUtil.sha256("Moovy" + user.getPassword()));

            // Then, save user
            repository.save(user);

            return Response
                .ok()
                .build()
            ;
        }
        else
        {
            return Response
                .status(Response.Status.CONFLICT)
                .build()
            ;
        }
    }
}
