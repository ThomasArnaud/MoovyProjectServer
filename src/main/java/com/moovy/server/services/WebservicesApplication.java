package com.moovy.server.services;

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
        this.singletons.add(new ExampleWebservice());

        this.singletons.add(new MoviesWebservice());

        this.singletons.add(new CategoriesWebservice());
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
