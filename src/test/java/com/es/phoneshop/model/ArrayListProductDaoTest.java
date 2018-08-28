package com.es.phoneshop.model;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class ArrayListProductDaoTest {
    ProductDao productDao = ArrayListProductDao.getInstance();


    @Test
    public void getInstance() throws Exception {
        ArrayListProductDao instance = ArrayListProductDao.getInstance();

        assertNotNull(instance);
    }

    @Test
    public void getProduct() throws Exception {
        long numId = 1L;
        productDao.save(new Product(numId, "code1", "decription1", new BigDecimal(10), 1));

        Long id = productDao.getProduct(numId).getId();

        assertEquals(1, id.longValue());
    }

    @Test
    public void findProducts() throws Exception {
        productDao.save(new Product(1L, "code1", "decription1", new BigDecimal(10), 1));

        List<Product> list = productDao.findProducts();

        assertFalse(list.isEmpty());
    }

    @Test
    public void save() throws Exception {
        long id = 4L;


        productDao.save(new Product(id, "code2", "decription2", new BigDecimal(10), 1));
        Product prod = productDao.getProduct(id);

        assertNotNull(prod);
    }

    @Test(expected = IllegalArgumentException.class)
    public void remove() throws Exception {
       long id = 4L;

        productDao.save(new Product(id, "code2", "decription2", new BigDecimal(10), 1));

        productDao.remove(id);
        productDao.getProduct(id);

    }

}