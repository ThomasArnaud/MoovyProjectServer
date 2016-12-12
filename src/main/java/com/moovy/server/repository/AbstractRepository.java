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
    protected final Class<Entity> entityClass;

    /**
     * Creates a new repository for a specific entity type.
     *
     * @param type The entity's type.
     */
    public AbstractRepository(Class<Entity> type)
    {
        this.entityClass = type;
    }

    /**
     * Fetches a single entity from the database.
     *
     * @param id The entity's id.
     * @return The wanted entity or {@code null} otherwise.
     * @throws HibernateException If an Hibernate error happens.
     */
    public Entity fetch(int id)
    throws HibernateException
    {
        try
        {
            return HibernateUtil.getSession().get(this.entityClass, id);
        }
        catch(HibernateException ex)
        {
            throw new RepositoryException(ex);
        }
    }

    /**
     * Fetches every entity of a specific type from the database.
     *
     * @return A list of the entities.
     * @throws HibernateException If an Hibernate error happens.
     */
    public List<Entity> fetchAll()
    throws HibernateException
    {
        // Initialize vars
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        List<Entity> entities = null;

        // Fetch the entities
        try
        {
            transaction = session.beginTransaction();
            entities = session.createQuery("FROM " + this.entityClass.getName(), this.entityClass).list();
            transaction.commit();

            return entities;
        }
        catch(HibernateException ex)
        {
            if(transaction != null)
            {
                transaction.rollback();
            }

            throw new RepositoryException(ex);
        }
    }

    /**
     * Saves an entity into the database.
     *
     * @param entity The entity to save.
     * @throws HibernateException If an Hibernate error happens.
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
        catch(HibernateException ex)
        {
            if(transaction != null)
            {
                transaction.rollback();
            }

            throw new RepositoryException(ex);
        }
    }

    /**
     * Deletes a single entity from the database.
     *
     * @param entity The entity to delete.
     * @throws HibernateException If an Hibernate error happens.
     */
    public void delete(Entity entity)
    throws HibernateException
    {
        // Initialize vars
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;

        // Save the entity
        try
        {
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        }
        catch(HibernateException ex)
        {
            if(transaction != null)
            {
                transaction.rollback();
            }

            throw new RepositoryException(ex);
        }
    }
}
