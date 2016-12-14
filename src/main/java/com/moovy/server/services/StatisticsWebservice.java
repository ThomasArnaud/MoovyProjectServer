package com.moovy.server.services;

import com.moovy.server.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.ws.WebServiceException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Thomas Arnaud (thomas.arnaud@etu.univ-lyon1.fr)
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 * @author Alexis Rabilloud (alexis.rabilloud@etu.univ-lyon1.fr)
 */

@Path("/statistics")
public class StatisticsWebservice
{
    @Context
    UriInfo uriInfo;

    /**
     *
     * @return Hashmap containing associations between tables and number of entries
     */
    @GET
    @Path("/dashboard")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDashboard()
    {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;

        Map<String, Long> map = new HashMap<>();
        map.put("Users", Long.MIN_VALUE);
        map.put("Movies", Long.MIN_VALUE);
        map.put("Actors", Long.MIN_VALUE);
        map.put("Directors", Long.MIN_VALUE);

        try
        {
            for(String s : map.keySet())
            {
                transaction = session.beginTransaction();
                map.remove(s);
                map.put(s,((session.createQuery("SELECT count(*) FROM " + s, Long.class).iterate().next())));
                transaction.commit();
            }
            return Response
                    .ok(map)
                    .build()
            ;
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
