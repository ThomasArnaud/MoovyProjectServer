package com.moovy.server.services;

import com.moovy.server.mappers.UnexpectedExceptionMapper;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Thomas Arnaud (thomas.arnaud@etu.univ-lyon1.fr)
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 * @author Alexis Rabilloud (alexis.rabilloud@etu.univ-lyon1.fr)
 */
@ApplicationPath("/")
public class WebservicesApplication extends Application
{
    /**
     *
     */
    protected Set<Object> singletons = new HashSet<>();

    /**
     *
     */
    protected Set<Class<?>> empty = new HashSet<>();

    /**
     * Initializes a new webservices application.
     */
    public WebservicesApplication()
    {
        // Define the available webservices
        this.singletons.add(new MoviesWebservice());
        this.singletons.add(new CategoriesWebservice());
        this.singletons.add(new ActorsWebservice());
        this.singletons.add(new DirectorsWebservice());
        this.singletons.add(new StatisticsWebservice());
        this.singletons.add(new UsersWebservice());
        this.singletons.add(new CharactersWebservice());

        // Define mappers
        this.singletons.add(new UnexpectedExceptionMapper());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Class<?>> getClasses()
    {
        return this.empty;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Object> getSingletons()
    {
        return this.singletons;
    }
}
