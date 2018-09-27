package com.es.phoneshop.model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ProductDao {
    Product getProduct(Long id);

    List<Product> findProducts(HttpServletRequest request);

    void save(Product product);

    void remove(Long id);
}
