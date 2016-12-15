package com.moovy.server.repository;

import com.moovy.server.model.Category;
import com.moovy.server.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * @author Thomas Arnaud (thomas.arnaud@etu.univ-lyon1.fr)
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 * @author Alexis Rabilloud (alexis.rabilloud@etu.univ-lyon1.fr)
 */
public class CategoryRepository extends AbstractRepository<Category>
{
    /**
     * Creates a new repository for categories.
     */
    public CategoryRepository()
    {
        super(Category.class);
    }

    /**
     * {@inheritDoc}
     */
    public boolean exists(int id)
    {
        throw new UnsupportedOperationException("A category's existence can't be tested with an id.");
    }

    /**
     * Tests if a category exists.
     *
     * @param code The category's code.
     * @return {@code true} if the category exists, {@code false} otherwise.
     */
    public boolean exists(String code)
    {
        // Initialize vars
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        boolean entityExists = false;

        // Tests the existence of a category
        try
        {
            transaction = session.beginTransaction();
            Query query = session.createQuery("SELECT 1 FROM " + this.entityClass.getName() + " WHERE code = ?1 LIMIT 1", Integer.class);
            query.setParameter(1, code);
            entityExists = query.uniqueResult() != null;
            transaction.commit();

            return entityExists;
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
     * {@inheritDoc}
     */
    @Override
    public Category fetch(int id)
    {
        throw new UnsupportedOperationException("A category can't be fetched with an id.");
    }

    /**
     * Fetches a single category from the database.
     *
     * @param code The category's code.
     * @return The wanted category or {@code null} otherwise.
     * @throws HibernateException If an Hibernate error happens.
     */
    public Category fetch(String code)
        throws HibernateException
    {
        try
        {
            return HibernateUtil.getSession().get(Category.class, code);
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
    public List<Category> lookup(String s)
    throws HibernateException
    {
        // Initialize vars
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        List<Category> entities = null;

        // Fetch the entities
        try
        {
            transaction = session.beginTransaction();
            Query query = session.createQuery("SELECT c FROM Category c WHERE c.name LIKE ?1", Category.class);
            query.setParameter(1, "%" + s + "%");
            entities = (List<Category>) query.list();
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
}
