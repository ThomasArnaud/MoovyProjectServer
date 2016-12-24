package com.moovy.server.repository;

import com.moovy.server.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.xml.ws.WebServiceException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Thomas Arnaud (thomas.arnaud@etu.univ-lyon1.fr)
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 * @author Alexis Rabilloud (alexis.rabilloud@etu.univ-lyon1.fr)
 */
public class StatisticsRepository
{
    /**
     * Fetches the statistics.
     *
     * @return The statistics.
     */
    public Map<String, Long> getDashboard()
    {
        // Initialize vars
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        Map<String, Long> statistics = new HashMap<>();

        try
        {
            transaction = session.beginTransaction();

            statistics.put("users", session.createQuery("SELECT count(*) FROM User", Long.class).iterate().next());
            statistics.put("actors", session.createQuery("SELECT count(*) FROM Actor ", Long.class).iterate().next());
            statistics.put("directors", session.createQuery("SELECT count(*) FROM Director ", Long.class).iterate().next());
            statistics.put("movies", session.createQuery("SELECT count(*) FROM Movie ", Long.class).iterate().next());

            transaction.commit();

            return statistics;
        }
        catch (HibernateException ex)
        {
            if (transaction != null)
            {
                transaction.rollback();
            }

            throw new WebServiceException(ex);
        }
    }
}
