package com.es.phoneshop.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayListProductDao implements ProductDao {
    private List<Product> productList;
    private static volatile ArrayListProductDao arrayListProductDao = null;
    private static Object lock = new Object();

    private ArrayListProductDao() {
        productList = new ArrayList<>();
        save(new Product(1L, "code", "decription", new BigDecimal(123), 1));
        save(new Product(2L, "code", "decription", new BigDecimal(-1), 1));
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
        Product prod = null;
        prod = productList.stream()
                .filter((p) -> p.getId().equals(id))
                .findAny()
                .get();
        return prod;
    }

    public synchronized List<Product> findProducts() {
        List<Product> arrayList = productList.stream()
                .filter((p) -> p.getPrice().compareTo(BigDecimal.ZERO) > 0 && p.getStock() > 0)
                .collect(Collectors.toList());
        return arrayList;
    }

    public synchronized void save(Product product) {
        productList.add(product);
    }

    public synchronized void remove(Long id) {
        productList.remove(getProduct(id));
    }
}
