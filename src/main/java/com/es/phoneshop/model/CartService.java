package com.es.phoneshop.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class CartService {
    private static final String CART_ATTRIBUTE_NAME = "cart";
    private static Object lock = new Object();
    private static volatile CartService cartService = null;

    private CartService() {
    }

    public static CartService getInstance() {
        if (cartService == null) {
            synchronized (lock) {
                if (cartService == null) {
                    cartService = new CartService();
                }
            }
        }
        return cartService;
    }

    public Cart getCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute(CART_ATTRIBUTE_NAME);
        if (cart == null) {
            cart = new Cart();
            session.setAttribute(CART_ATTRIBUTE_NAME, cart);
        }
        return cart;
    }

    public void add(HttpServletRequest request, Product product, int quantity) {
        addOrUpdate(request, product, quantity, true);
    }

    public void update(HttpServletRequest request, Product product, int quantity) {
        addOrUpdate(request, product, quantity, false);
    }

    private void addOrUpdate(HttpServletRequest request, Product product, int quantity, boolean add) {
        Cart cart = getCart(request);
        List<CartItem> cartItems = cart.getCartItems();

        Optional<CartItem> cartItemOptional = cartItems.stream()
                .filter(p -> p.getProduct().equals(product))
                .findAny();
        if (cartItemOptional.isPresent()) {
            CartItem cartItem = cartItemOptional.get();
            int newQuantity = add ? cartItem.getQuantity() + quantity : quantity;
            cartItem.setQuantity(newQuantity);
        } else {
            cart.getCartItems().add(new CartItem(product, quantity));
        }
    }

    public void deleteProduct(Cart cart, Product product, int count) {
        List<CartItem> cartItems = cart.getCartItems();
        cartItems.remove(cartItems.get(count));
    }
}
