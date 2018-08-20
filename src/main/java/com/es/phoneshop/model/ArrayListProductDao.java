package com.es.phoneshop.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayListProductDao implements ProductDao {
    private static ArrayList<Product> productList;

    public ArrayListProductDao() {
        productList = getInstance();
        Product product = new Product(1l, "code", "decription", new BigDecimal(123), 1);
        save(product);
        Product product1 = new Product(1l, "code", "decription", new BigDecimal(-1), 1);
        save(product1);
    }

    public static ArrayList<Product> getInstance() {
        if (productList == null) {
            synchronized (Product.class) {
                if (productList == null)
                    productList = new ArrayList<Product>();
            }
        }
        return productList;
    }

    public synchronized Product getProduct(Long id) {
        Product prod = null;
      /*  for (Product product : productList) {
            if (product.getId().equals(id))
                prod = product;
        }
        return prod;*/
        prod = productList.stream().filter((p) -> p.getId().equals(id)).findAny().get();
        return prod;
    }

    public synchronized List<Product> findProducts() {
        List<Product> arrayList = productList.stream().filter((p) -> p.getPrice().compareTo(BigDecimal.ZERO) > 0 && p.getStock() > 0).collect(Collectors.toList());
        return arrayList;
    }

    public synchronized void save(Product product) {
        productList.add(product);
    }

    public synchronized void remove(Long id) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId().equals(id)) {
                productList.remove(i);
            }
        }
    }
}
