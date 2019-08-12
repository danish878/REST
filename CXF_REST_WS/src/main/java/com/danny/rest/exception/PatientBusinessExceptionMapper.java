package com.danny.rest.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class PatientBusinessExceptionMapper implements ExceptionMapper<PatientBusinessException> {
    @Override
    public Response toResponse(PatientBusinessException e) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"status\": \"error\"");
        sb.append(",");
        sb.append("\"message\": \"Resource does not exist\"");
        sb.append("}");

        return Response.status(404).entity(sb.toString()).type(MediaType.APPLICATION_JSON).build();
    }
}
