package com.danny.swagger;

import io.swagger.annotations.Api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api
@Path("/")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public interface PatientService {

    @GET
    @Path("/patients/{id}/")
    public abstract Patient getPatient(@PathParam("id") String id);

    @PUT
    @Path("/patients/")
    public abstract Response updatePatient(Patient patient);

    @POST
    @Path("/patients/")
    public abstract Response addPatient(Patient patient);

    @DELETE
    @Path("/patients/{id}/")
    public abstract Response deletePatients(@PathParam("id") String id);

    @Path("/prescriptions/{id}/")
    public abstract Prescription getPrescription(@PathParam("id") String prescriptionId);

}