package com.moovy.server.services;

import com.moovy.server.model.Movie;
import com.moovy.server.repository.MovieRepository;

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
@Path("/movies")
public class MoviesWebservice
{
    /**
     *
     */
    @Context
    UriInfo uriInfo;

    /**
     * Produces a list of movies that can be filtered through a "query" parameter.
     *
     * @return The list of movies.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getList(@QueryParam("query") String query)
    {
        // Initialize vars
        MovieRepository repository = new MovieRepository();

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
     * Produces a single movie.
     *
     * @return The movie.
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOne(@PathParam("id") int id)
    {
        Movie movie = new MovieRepository().fetch(id);

        if(movie != null)
        {
            return Response
                .ok(movie)
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
     * Adds a new movie to the database.
     *
     * @return A success or failure response.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Movie movie)
    {
        // Save the new movie
        new MovieRepository().save(movie);

        // Build response
        UriBuilder uriBuilder = this.uriInfo.getAbsolutePathBuilder();
        uriBuilder.path("/movies/" + movie.getId());

        return Response
            .created(uriBuilder.build())
            .build()
        ;
    }

    /**
     * Updates a movie from the database.
     *
     * @return A success or failure response.
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Movie movie)
    {
        // Update the movie
        new MovieRepository().save(movie);

        // Build response
        return Response
            .ok()
            .build()
        ;
    }

    /**
     * Deletes a movie from the database.
     *
     * @param id The movie's id.
     * @return A success or failure response.
     */
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id)
    {
        // Initialize vars
        MovieRepository repository = new MovieRepository();

        // Does the movie exist?
        Movie movie = repository.fetch(id);

        if(movie != null)
        {
            // Delete the movie
            repository.delete(movie);

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
