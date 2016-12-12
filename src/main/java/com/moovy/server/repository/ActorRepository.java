package com.moovy.server.repository;

import com.moovy.server.model.Actor;

/**
 * @author Thomas Arnaud (thomas.arnaud@etu.univ-lyon1.fr)
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 * @author Alexis Rabilloud (alexis.rabilloud@etu.univ-lyon1.fr)
 */
public class ActorRepository extends AbstractRepository<Actor>
{
    public ActorRepository()
    {
        super(Actor.class);
    }

    /*
    public Actor getActorById(int id)
    {
        return HibernateUtil.getSession().get(Actor.class, id);
    }

    public List<Actor> getActors()
    {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        List actors = session.createQuery("from Actor").list();
        session.getTransaction().commit();

        return (List<Actor>) actors;
    }
    */
}
