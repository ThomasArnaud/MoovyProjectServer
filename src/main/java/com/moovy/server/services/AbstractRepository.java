package com.moovy.server.services;

import com.moovy.server.utils.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

/**
 * @author Thomas Arnaud (thomas.arnaud@etu.univ-lyon1.fr)
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 * @author Alexis Rabilloud (alexis.rabilloud@etu.univ-lyon1.fr)
 */
public abstract class AbstractRepository<Entity> {

    private final Class<Entity> entityClass;

    public AbstractRepository(Class<Entity> entity) {
        this.entityClass = entity;
    }

    public List<Entity> fetchAll() {
        Session session = HibernateUtil.openSession();

        session.beginTransaction();
        String query = "FROM " + entityClass.getName();
        List<Entity> entities = (List<Entity>) session.createQuery(query).list();
        session.getTransaction().commit();

        session.close();

        return entities;
    }

    public Entity fetch(int id) {
        Session session = HibernateUtil.openSession();

        session.beginTransaction();
        Entity entity = (Entity) session.get(entityClass, id);
        session.getTransaction().commit();

        session.close();
        return entity;
    }

    public void saveOrUpdate(Entity entity) {
        Session session = HibernateUtil.openSession();

        session.saveOrUpdate(entity);
        session.getTransaction().commit();

        session.close();
    }
}
