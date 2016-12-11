package com.moovy.server.repository;

import com.moovy.server.model.Movie;
import com.moovy.server.services.AbstractRepository;
import com.moovy.server.utils.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;

/**
 * @author Thomas Arnaud (thomas.arnaud@etu.univ-lyon1.fr)
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 * @author Alexis Rabilloud (alexis.rabilloud@etu.univ-lyon1.fr)
 */

public class MovieRepository extends AbstractRepository<Movie> {


    public MovieRepository() {
        super(Movie.class);
    }

    /**
     *
     * @param id
     */
    public void delete(int id) {
        Session session = HibernateUtil.openSession();
        session.beginTransaction();
        String query = "FROM Movie m WHERE m.id=" + id;
        List result = session.createQuery(query).list();
        if (result.size() == 1) {
            result.remove(0);
        }
        else {
            System.err.println("Couldn't delete movie element of id " + id);
        }
        session.close();
    }

    /**
     *
     * @param s
     * @return
     */
    public List<Movie> lookup(String s) {
        Session session = HibernateUtil.openSession();

        session.beginTransaction();
        Query query = session.createQuery("SELECT m FROM Movie m WHERE m.title LIKE ?1");
        query.setParameter(1, "%"+s+"%");

        List<Movie> results = (List<Movie>) query.getResultList();

        return results;
    }
}
