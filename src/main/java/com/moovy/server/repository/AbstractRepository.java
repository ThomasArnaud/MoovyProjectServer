package com.moovy.server.repository;

import com.moovy.server.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * @author Thomas Arnaud (thomas.arnaud@etu.univ-lyon1.fr)
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 * @author Alexis Rabilloud (alexis.rabilloud@etu.univ-lyon1.fr)
 */
public abstract class AbstractRepository<Entity>
{
    /**
     * The repository's entity's type.
     */
    private final Class<Entity> entityClass;

    /**
     * Creates a new repository for a specific entity type.
     *
     * @param entityClass The entity's type.
     */
    public AbstractRepository(Class<Entity> entityClass)
    {
        this.entityClass = entityClass;
    }

    /**
     * Fetches a single entity from the database thanks to its id.
     *
     * @param id The entity's id.
     * @return The wanted entity or {@code null} otherwise.
     * @throws HibernateException If a Hibernate error happens.
     */
    public Entity fetch(int id)
    throws HibernateException
    {
        return HibernateUtil.getSession().get(this.entityClass, id);

        /*
        Session session = HibernateUtil.getSession();

        session.beginTransaction();
        Entity entity = session.get(this.entityClass, id);
        session.getTransaction().commit();
        //session.close();

        return entity;
        */
    }

    /**
     * Fetches every entity of a specific type from the database.
     *
     * @return A list of all the entities.
     * @throws HibernateException If a Hibernate error happens.
     */
    public List<Entity> fetchAll()
    throws HibernateException
    {
        // Initialize vars
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        List<Entity> results = null;

        // Save the entity
        try
        {
            transaction = session.beginTransaction();
            results = session.createQuery("FROM " + this.entityClass.getSimpleName(), this.entityClass).list();
            transaction.commit();

            return results;
        }
        catch(HibernateException e)
        {
            if(transaction != null)
            {
                transaction.rollback();
            }

            throw e;
        }

        /*
        Session session = HibernateUtil.getSession();

        session.beginTransaction();
        List<Entity> entities = (List<Entity>) session.createQuery("FROM " + this.entityClass.getSimpleName()).list();
        session.getTransaction().commit();
        //session.close();

        return entities;
        */
    }

    /**
     * Saves an entity into the database.
     *
     * @param entity The entity to save.
     * @throws HibernateException If a Hibernate error happens.
     */
    public void save(Entity entity)
    throws HibernateException
    {
        // Initialize vars
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;

        // Save the entity
        try
        {
            transaction = session.beginTransaction();
            session.saveOrUpdate(entity);
            transaction.commit();
        }
        catch(HibernateException e)
        {
            if(transaction != null)
            {
                transaction.rollback();
            }

            throw e;
        }
        /*
        Session session = HibernateUtil.getSession();

        session.beginTransaction();
        session.saveOrUpdate(entity);
        session.getTransaction().commit();
        //session.close();
        */
    }

    /**
     * Deletes an entity from the database.
     *
     * @param entity The entity to delete.
     * @throws HibernateException If a Hibernate error happens.
     */
    public void delete(Entity entity)
    throws HibernateException
    {
        // Initialize vars
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;

        // Delete the entity
        try
        {
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        }
        catch(HibernateException e)
        {
            if(transaction != null)
            {
                transaction.rollback();
            }

            throw e;
        }
        /*
        Session session = HibernateUtil.getSession();

        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
        //session.close();
        */
    }
}
