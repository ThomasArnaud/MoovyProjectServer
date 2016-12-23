package com.moovy.server.mappers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author Thomas Arnaud (thomas.arnaud@etu.univ-lyon1.fr)
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 * @author Alexis Rabilloud (alexis.rabilloud@etu.univ-lyon1.fr)
 */
@Provider
public class UnexpectedExceptionMapper implements ExceptionMapper<Exception>
{
    @Override
    public Response toResponse(Exception exception)
    {
        return Response
            .status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity(exception)
            .type(MediaType.APPLICATION_JSON_TYPE)
            .build()
        ;
    }
}
