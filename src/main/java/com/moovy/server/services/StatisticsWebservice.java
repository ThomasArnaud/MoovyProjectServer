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
import java.util.List;

/**
 * @author Thomas Arnaud (thomas.arnaud@etu.univ-lyon1.fr)
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 * @author Alexis Rabilloud (alexis.rabilloud@etu.univ-lyon1.fr)
 */

@Path("/statistics")
public class StatisticsWebservice {

    @Context
    UriInfo uriInfo;

    /**
     *
     * @return List of counters containing number of entries in main tables
     */
    @GET
    @Path("/dashboard")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDashboard() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;

        List<Integer> counts = null;
        List<String> countsCategory = null;
        countsCategory.add("Users");
        countsCategory.add("Movies");
        countsCategory.add("Actors");
        countsCategory.add("Directors");

        try {
            transaction = session.beginTransaction();
            for(String s : countsCategory) {
                counts.add( ((Integer) session.createQuery("SELECT count(*) FROM " + s).iterate().next()).intValue());
            }
            return Response
                    .ok(counts)
                    .build()
            ;
        }
        catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new WebServiceException(ex);
        }
    }
}
