package com.moovy.server.repository;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * @author Thomas Arnaud (thomas.arnaud@etu.univ-lyon1.fr)
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 * @author Alexis Rabilloud (alexis.rabilloud@etu.univ-lyon1.fr)
 */
public class RepositoryException extends WebApplicationException
{
    /**
     *
     * @param cause
     */
    public RepositoryException(Throwable cause)
    {
        super(
            Response
            .status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity(cause)
            .build()
        );
    }
}
