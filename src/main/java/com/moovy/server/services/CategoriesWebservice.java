package com.moovy.server.services;

import com.moovy.server.model.Category;
import com.moovy.server.repository.CategoryRepository;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;


/**
 * @author Thomas Arnaud (thomas.arnaud@etu.univ-lyon1.fr)
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 * @author Alexis Rabilloud (alexis.rabilloud@etu.univ-lyon1.fr)
 */

@Path("/categories")
public class CategoriesWebservice
{
    private CategoryRepository repository;

    public CategoriesWebservice()
    {
        this.repository = new CategoryRepository();
    }

    /**
     *
     * @return
     */
    @GET
    // @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> categoriesList()
    {
        return repository.fetchAll();
    }

    /**
     *
     * @param id
     * @return
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Category getCategory(@PathParam("id") int id)
    {
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
    public String searchCategory(@PathParam("string") String query)
    {
        return "test";
    }


    /**
     *
     * @param category
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateCategory(Category category)
    {
        repository.save(category);
    }


    /**
     *
     */
    @POST
    // @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addCategory(Category category)
    {
        repository.save(category);
    }

    /**
     *
     * @param id
     */
    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteCategory(@PathParam("id") int id)
    {
        // repository.delete(id);
    }



}
