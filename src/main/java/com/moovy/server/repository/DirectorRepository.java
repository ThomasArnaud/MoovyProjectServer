package com.moovy.server.repository;

import com.moovy.server.model.Director;
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
public class DirectorRepository extends AbstractRepository<Director>
{
    public DirectorRepository()
    {
        super(Director.class);
    }

    /**
     * Fetches every entity of a specific type from the database.
     *
     * @return A list of the entities.
     * @throws HibernateException If an Hibernate error happens.
     */
    public List<Director> lookup(String s)
    throws HibernateException
    {
        // Initialize vars
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        List<Director> entities = null;

        // Fetch the entities
        try
        {
            transaction = session.beginTransaction();
            Query query = session.createQuery("SELECT d FROM Director d WHERE d.firstName LIKE ?1 OR d.lastName LIKE ?1", Director.class);
            query.setParameter(1, "%" + s + "%");
            entities = (List<Director>) query.list();
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
