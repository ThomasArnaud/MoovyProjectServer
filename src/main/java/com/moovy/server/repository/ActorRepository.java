package com.moovy.server.repository;

import com.moovy.server.model.Actor;
import com.moovy.server.utils.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

/**
 * @author Thomas Arnaud (thomas.arnaud@etu.univ-lyon1.fr)
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 * @author Alexis Rabilloud (alexis.rabilloud@etu.univ-lyon1.fr)
 */
public class ActorRepository {

    public Actor getActorById(int id) {
        return HibernateUtil.openSession().get(Actor.class, id);
    }

    public List<Actor> getActors() {
        Session session = HibernateUtil.openSession();
        session.beginTransaction();
        List actors = session.createQuery("from Actor").list();
        session.getTransaction().commit();

        return (List<Actor>) actors;
    }
}
