package com.danny.rest.service;

import com.danny.rest.model.Product;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;

@Path("/productService/products")
public interface ProductService {

    @GET
    List<Product> getProducts();

    @POST
    long addProduct(Product product);
}
