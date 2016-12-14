package com.moovy.server.repository;

import com.moovy.server.model.User;
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

public class UserRepository extends AbstractRepository<User>
{

    public UserRepository() { super(User.class); }

    public User fetchMail(String email) {

        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        List<User> user;

        try
        {
            transaction = session.beginTransaction();
            Query query = session.createQuery("SELECT u FROM User u WHERE u.email=" + email);
            user = query.list();

            if (user.size() != 1)
            {
                if (user.size() == 0)
                {
                    System.out.println("User not found");
                }
                else
                {
                    System.out.println("Duplicate mail found");

                }
                return null;
            }
            else
            {
                return user.get(0);
            }
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
