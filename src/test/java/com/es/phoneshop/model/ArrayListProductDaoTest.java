package com.es.phoneshop.model;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ArrayListProductDaoTest {
    ProductDao productDao;
    @Before
    public void setUp() throws Exception {
        productDao = ArrayListProductDao.getInstance();
        productDao.save(new Product(1L, "code1", "decription1", new BigDecimal(10), 1));
        productDao.save(new Product(2L, "code2", "decription2", new BigDecimal(10), 1));
        productDao.save(new Product(3L, "code3", "decription3", new BigDecimal(10), 1));

    }

    @Test
    public void getInstance() throws Exception {
        assertNotNull(ArrayListProductDao.getInstance());
    }

    @Test
    public void getProduct() throws Exception {
        assertEquals(1, productDao.getProduct(1L).getId().longValue());
    }

    @Test
    public void findProducts() throws Exception {
       assertTrue(!productDao.findProducts().isEmpty());
    }

    @Test
    public void save() throws Exception {
        productDao.save(new Product(4L, "code2", "decription2", new BigDecimal(10), 1));
        assertNotNull(productDao.getProduct(4L));
    }

    @Test
    public void remove() throws Exception {
        productDao.remove(4L);
        assertNull(productDao.getProduct(4L));
    }

}