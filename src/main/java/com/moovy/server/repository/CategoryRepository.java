package com.moovy.server.repository;

import com.moovy.server.model.Category;
import com.moovy.server.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

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
    @Override
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
        boolean entityExists = false;

        System.out.println("beforeGet:" + session.isJoinedToTransaction());

        // Tests the existence of an entity
        Category entity = session.get(Category.class, code);
        System.out.println("afterGet:" + session.isJoinedToTransaction());
        entityExists = entity != null;
        System.out.println("beforeDetach:" + session.isJoinedToTransaction());
        session.detach(entity);
        System.out.println("afterDetach:" + session.isJoinedToTransaction());

        return entityExists;
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
}
