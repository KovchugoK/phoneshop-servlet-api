package com.es.phoneshop.order;

import com.es.phoneshop.model.Cart;

public interface OrderServiceInterface {
    void placeOrder(Cart cart, String name, String adress, String phone);

    Order getOrder(String orderId);
}
