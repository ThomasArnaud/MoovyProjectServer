package com.moovy.server.repository;

import com.moovy.server.utils.HibernateUtil;
import org.apache.log4j.Logger;
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
     * The class logger.
     */
    protected static Logger logger = Logger.getLogger(AbstractRepository.class);
    
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
     * Tests if an entity exists.
     *
     * @param id The entity's id.
     * @return {@code true} if the entity exists, {@code false} otherwise.
     */
    public boolean exists(int id)
    {
        // Log method
        logger.debug(this.entityClass.getSimpleName() + ".exists(" + id + ")");

        // Initialize vars
        Session session = HibernateUtil.getSession();
        boolean entityExists = false;

        // Tests the existence of an entity
        Entity entity = session.get(this.entityClass, id);
        entityExists = entity != null;

        if(entityExists)
        {
            session.detach(entity);
        }

        return entityExists;
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
        // Log method
        logger.debug(this.entityClass.getSimpleName() + ".fetch(" + id + ")");

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
        // Log method
        logger.debug(this.entityClass.getSimpleName() + ".fetchAll()");

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
        // Log method
        logger.debug(this.entityClass.getSimpleName() + ".save(" + entity + ")");

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
        // Log method
        logger.debug(this.entityClass.getSimpleName() + ".delete(" + entity + ")");

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

    /**
     * Detaches an entity from the Hibernate session.
     *
     * @param entity The entity to detach.
     * @throws HibernateException If an Hibernate error happens.
     */
    public void detach(Entity entity)
    throws HibernateException
    {
        // Log method
        logger.debug(this.entityClass.getSimpleName() + ".detach(" + entity + ")");

        // Detach entity from the session
        HibernateUtil.getSession().detach(entity);
    }
}
