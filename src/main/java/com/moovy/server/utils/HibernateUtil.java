package com.moovy.server.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * @author Thomas Arnaud (thomas.arnaud@etu.univ-lyon1.fr)
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 * @author Alexis Rabilloud (alexis.rabilloud@etu.univ-lyon1.fr)
 */
public class HibernateUtil
{
    /**
     * The Hibernate session factory.
     */
    protected static Session session = null;

    static
    {
        StandardServiceRegistry registry =
            new StandardServiceRegistryBuilder()
                .configure()
                .build()
            ;

        try
        {
            SessionFactory factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            HibernateUtil.session = factory.openSession();
        }
        catch(Exception e)
        {
            StandardServiceRegistryBuilder.destroy(registry);

            throw e;
        }
    }

    /**
     * Opens a new Hibernate session.
     *
     * @return A Hibernate session.
     */
    public static Session getSession()
    {
        return HibernateUtil.session;
    }
}
