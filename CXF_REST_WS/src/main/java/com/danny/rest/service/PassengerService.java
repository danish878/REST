package com.danny.rest.service;

import com.danny.rest.model.Passenger;
import com.danny.rest.model.Patient;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Path("/passengerService/passengers")
public interface PassengerService {

    @GET
    List<Passenger> getPassengers(@QueryParam("start") int start, @QueryParam("size") int size);

    @Path("/{id}")
    @GET
    Passenger getPassengerById(@PathParam("id") long id);


    @POST
    Response createPassenger(@FormParam("id") long id, @FormParam("name") String name, @HeaderParam("agent") String agent, @Context HttpHeaders headers);

    @PUT
    Response updatePassenger(Passenger passenger);

    @Path("/{id}")
    @DELETE
    Response deletePassenger(@PathParam("id") long id);
}
