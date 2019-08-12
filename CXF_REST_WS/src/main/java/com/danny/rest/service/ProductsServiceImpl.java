package com.danny.rest.service;

import com.danny.rest.model.Products;
import com.danny.rest.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Service
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private ProductRepository repository;

    @Override
    public List<Products> getProducts() {
        return repository.findAll();
    }

    @Override
    public Response getProduct(int id) {
        Optional<Products> product = repository.findById(id);
        Response response;

        if (product.isPresent())
            response = Response.status(Response.Status.OK).entity(product.get()).build();
        else
            response = Response.status(Response.Status.NOT_FOUND).build();

        return response;
    }

    @Override
    public Response createProduct(Products product) {
        repository.save(product);

        return Response.status(Response.Status.CREATED).build();
    }

    @Override
    public Response updateProduct(Products p) {
        Optional<Products> product = repository.findById(p.getId());
        Response response;

        if (product.isPresent()) {
            repository.save(p);
            response = Response.status(Response.Status.OK).build();
        } else
            response = Response.status(Response.Status.NOT_FOUND).build();

        return response;
    }

    @Override
    public Response deleteProduct(int id) {
        Optional<Products> product = repository.findById(id);
        Response response;

        if (product.isPresent()) {
            repository.deleteById(id);
            response = Response.status(Response.Status.OK).build();
        } else
            response = Response.status(Response.Status.NOT_FOUND).build();

        return response;
    }
}
