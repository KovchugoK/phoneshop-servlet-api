package com.es.phoneshop.order;

import com.es.phoneshop.model.Cart;
import com.es.phoneshop.model.CartItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class OrderService implements OrderServiceInterface {

    private List<Order> orders = new ArrayList<>();

    Random random = new Random();
    private static Object lock = new Object();
    private static volatile OrderService orderService = null;

    private OrderService() {
    }

    public static OrderService getInstance() {
        if (orderService == null) {
            synchronized (lock) {
                if (orderService == null) {
                    orderService = new OrderService();
                }
            }
        }
        return orderService;
    }

    @Override
    public Order placeOrder(Cart cart, String name, String address, String phone) {
        Order order = new Order();
        order.setName(name);
        order.setAddress(address);
        order.setPhone(phone);
        order.setCartItems(cart.getCartItems());
        order.setOrderId(generateOrderId());
        orders.add(order);
        return order;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public Order getOrder(String orderId) {
        return orders.stream()
                .filter((p) -> p.getOrderId().equals(orderId))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("There is no element with such id"));
    }


    public String generateOrderId() {
        StringBuilder orId = new StringBuilder();
        int lenOfIdNumber = 10;
        for (int i = 0; i < lenOfIdNumber; i++) {
            orId.append(1 + random.nextInt(9));
        }
        return orId.toString();
    }
}
