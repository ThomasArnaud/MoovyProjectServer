package com.moovy.server.services;

import com.moovy.server.model.Character;
import com.moovy.server.model.Movie;
import com.moovy.server.repository.MovieRepository;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

/**
 * @author Thomas Arnaud (thomas.arnaud@etu.univ-lyon1.fr)
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 * @author Alexis Rabilloud (alexis.rabilloud@etu.univ-lyon1.fr)
 */
@Path("/characters")
public class CharactersWebservice
{
    /**
     *
     */
    @Context
    UriInfo uriInfo;

    /**
     * Fetches a movie's characters.
     *
     * @param movieId The movie's id.
     * @return The list of characters.
     */
    @GET
    @Path("/movies/{movieId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByMovie(@PathParam("movieId") int movieId)
    {
        Movie movie = new MovieRepository().fetch(movieId);

        if(movie != null)
        {
            return Response
                .ok(movie.getCharacters())
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
     *
     * @param movieId The movie's id.
     * @return A success or failure response.
     */
    @PUT
    @Path("/movies/{movieId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@PathParam("movieId") int movieId, List<Character> characters)
    {
        // Initialize vars
        MovieRepository repository = new MovieRepository();
        Movie movie = repository.fetch(movieId);

        if(movie != null)
        {
            // Define characters
            movie.setCharacters(characters);

            // Update the movie
            repository.save(movie);

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
}
