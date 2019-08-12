package com.danny.rest.service;

import com.danny.rest.model.Products;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/productService/products")
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public interface ProductsService {

    @GET
    List<Products> getProducts();

    @GET
    @Path("{id}")
    Response getProduct(@PathParam("id") int id);

    @POST
    Response createProduct(Products product);

    @PUT
    Response updateProduct(Products product);

    @DELETE
    @Path("{id}")
    Response deleteProduct(@PathParam("id") int id);
}
