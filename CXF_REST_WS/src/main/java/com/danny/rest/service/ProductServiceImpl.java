package com.danny.rest.service;

import com.danny.rest.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private List<Product> products = new ArrayList<>();
    private long id = 123;

    public ProductServiceImpl() {
        products.add(new Product(id++, "Product1"));
    }

    @Override
    public List<Product> getProducts() {
        return products;
    }

    @Override
    public long addProduct(Product product) {
        product.setId(id++);
        products.add(product);

        return product.getId();
    }
}
