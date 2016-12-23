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
}
