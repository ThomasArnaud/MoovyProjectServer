package com.moovy.server.repository;

import com.moovy.server.model.User;
import com.moovy.server.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * @author Thomas Arnaud (thomas.arnaud@etu.univ-lyon1.fr)
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 * @author Alexis Rabilloud (alexis.rabilloud@etu.univ-lyon1.fr)
 */
public class UserRepository extends AbstractRepository<User>
{
    /**
     * Creates a new repository for users.
     */
    public UserRepository()
    {
        super(User.class);
    }

    /**
     *
     * @param email
     * @return
     */
    public boolean exists(String email)
    {
        // Initialize vars
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        int usersNumber;

        // Fetch the user
        try
        {
            transaction = session.beginTransaction();
            Query query = session.createQuery("SELECT count(u) FROM User u WHERE u.email = ?1", Long.class);
            query.setParameter(1, email);
            query.setMaxResults(1);
            usersNumber = ((Long) query.getSingleResult()).intValue();
            transaction.commit();

            return usersNumber > 0;
        }
        catch(HibernateException ex)
        {
            if (transaction != null)
            {
                transaction.rollback();
            }

            throw new RepositoryException(ex);
        }
    }

    /**
     * Fetches an user by their email address.
     *
     * @param email The user's email address.
     * @return Ther wanted user, or {@code false} otherwise.
     */
    public User fetchByEmail(String email)
    {
        // Initialize vars
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        User user = null;

        // Fetch the user
        try
        {
            transaction = session.beginTransaction();
            Query query = session.createQuery("SELECT u FROM User u WHERE u.email = ?1", User.class);
            query.setParameter(1, email);
            query.setMaxResults(1);
            user = (User) query.getSingleResult();
            transaction.commit();

            return user;
        }
        catch(HibernateException ex)
        {
            if (transaction != null)
            {
                transaction.rollback();
            }

            throw new RepositoryException(ex);
        }
    }
}
