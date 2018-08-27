package com.es.phoneshop.web;

import com.es.phoneshop.model.ArrayListProductDao;
import com.es.phoneshop.model.Product;
import com.es.phoneshop.model.ProductDao;

import javax.servlet.*;
import java.math.BigDecimal;

public class SampleDataServletContextListener implements ServletContextListener {
    private ProductDao productDao;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        productDao = ArrayListProductDao.getInstance();
        productDao.save(new Product(1L, "code1", "decription1", new BigDecimal(10), 1));
        productDao.save(new Product(2L, "code2", "decription2", new BigDecimal(10), 1));
        productDao.save(new Product(3L, "code3", "decription3", new BigDecimal(10), 1));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }


}
