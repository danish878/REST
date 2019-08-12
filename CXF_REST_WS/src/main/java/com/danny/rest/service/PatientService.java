package com.danny.rest.service;

import com.danny.rest.model.Patient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Path("/patientService/patients")
public interface PatientService {

    @GET
    List<Patient> getPatients();

    @Path("/{patientId}")
    @GET
    Patient getPatientById(@PathParam("patientId") long id);

    /**
     * @param patient Content-Type : application/json|xml; odata=verbose => for dev
     *                Content-Type : application/json|xml; odata=nometadata => for prod
     *                Accept : application/json|xml
     * @return
     */
    @POST
    Response createPatient(Patient patient);

    @PUT
    Response updatePatient(Patient patient);

    @Path("/{patientId}")
    @DELETE
    Response deletePatient(@PathParam("patientId") long id);
}
