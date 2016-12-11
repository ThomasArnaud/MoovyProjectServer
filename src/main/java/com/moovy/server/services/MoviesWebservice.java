package com.moovy.server.services;

import com.moovy.server.model.Movie;
import com.moovy.server.repository.MovieRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author Thomas Arnaud (thomas.arnaud@etu.univ-lyon1.fr)
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 * @author Alexis Rabilloud (alexis.rabilloud@etu.univ-lyon1.fr)
 */
@Path("/movies")
public class MoviesWebservice
{
    private MovieRepository repository;


    public MoviesWebservice() {
        this.repository = new MovieRepository();
    }

    /**
     *
     * @return
     */
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movie> moviesList() {
        return repository.fetchAll();
    }

    /**
     *
     * @return
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Movie movie(@PathParam("id") int id) {
        return repository.fetch(id);
    }

    /**
     *
     * @param query
     * @return
     */
    @GET
    @Path("?query={string}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movie> searchMovie(@PathParam("string") String query) {
        return repository.lookup(query);
    }

    /**
     *
     *
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateMovie(Movie movie) {
        repository.update(movie);
    }

    /**
     *
     *
     */
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addMovie(Movie movie) {
        repository.save(movie);
    }

    /**
     *
     * @param id
     */
    @DELETE
    @Path("/{id}")
    public void deleteMovie(@PathParam("id") int id) {
        repository.delete(id);
    }
}
