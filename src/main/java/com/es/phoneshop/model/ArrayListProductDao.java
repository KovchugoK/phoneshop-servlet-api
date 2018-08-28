package com.es.phoneshop.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

public class ArrayListProductDao implements ProductDao {
    private List<Product> productList = new ArrayList<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private static volatile ArrayListProductDao arrayListProductDao = null;

    private static Object lock = new Object();


    private ArrayListProductDao() {
    }

    public static ArrayListProductDao getInstance() {
        if (arrayListProductDao == null) {
            synchronized (lock) {
                if (arrayListProductDao == null) {
                    arrayListProductDao = new ArrayListProductDao();
                }
            }
        }
        return arrayListProductDao;
    }

    public synchronized Product getProduct(Long id) {
        return productList.stream()
                .filter((p) -> p.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("There is no element with such id"));
    }

    public synchronized List<Product> findProducts() {
        return productList.stream()
                .filter((p) -> p.getPrice().compareTo(BigDecimal.ZERO) > 0 && p.getStock() > 0)
                .collect(Collectors.toList());
    }

    public synchronized void save(Product product) {
            productList.remove(product);
            productList.add(product);

    }

    public synchronized void remove(Long id) {
        productList.remove(getProduct(id));
    }
}
