package com.danny.rest.service;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

@Path("/fileService")
public interface FileService {

    @Path("/upload")
    @POST
    void upload(List<Attachment> attachments) throws IOException;

    @Path("/download")
    @GET
    Response download();

}