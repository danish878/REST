package com.danny.rest.service;

import com.danny.rest.model.ChecksList;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

@Path("/checkProcessingService")
public interface CheckProcessor {

    @POST
    @Path("/processChecks")
    void processChecks(@Suspended AsyncResponse response, ChecksList checksList);

    @GET
    @Path("/checks")
    void getChecks(@Suspended AsyncResponse response);
}
