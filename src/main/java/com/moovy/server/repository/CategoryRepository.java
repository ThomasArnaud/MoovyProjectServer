package com.moovy.server.repository;

import com.moovy.server.model.Category;

/**
 * @author Thomas Arnaud (thomas.arnaud@etu.univ-lyon1.fr)
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 * @author Alexis Rabilloud (alexis.rabilloud@etu.univ-lyon1.fr)
 */
public class CategoryRepository extends AbstractRepository<Category>
{
    public CategoryRepository()
    {
        super(Category.class);
    }

    /*
    public void delete(int id)
    {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        String query = "FROM Category c WHERE c.id=" +id;

        List result = session.createQuery(query).list();

        if (result.size() == 1) {
            result.remove(0);
        }
        else {
            System.err.println("Couldn't delete category element of id " +id);
        }
    }
    */
}
