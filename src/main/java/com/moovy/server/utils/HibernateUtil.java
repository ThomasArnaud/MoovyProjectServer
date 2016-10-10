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
    protected static SessionFactory factory = null;

    /**
     * Opens a new Hibernate session.
     *
     * @return A Hibernate session.
     */
    public static Session openSession()
    {
        if(HibernateUtil.factory == null)
        {
            StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder()
                .configure()
                .build()
            ;

            try
            {
                HibernateUtil.factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            }
            catch(Exception e)
            {
                StandardServiceRegistryBuilder.destroy(registry);
            }
        }

        return HibernateUtil.factory.openSession();
    }
}
