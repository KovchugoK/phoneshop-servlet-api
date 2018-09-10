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
            for (Product product : ArrayListProductDao.getInstance().findProducts()) {
                add(cart, product, 0);
            }
            session.setAttribute(CART_ATTRIBUTE_NAME, cart);
        }
        return cart;
    }

    public void add(Cart cart, Product product, int quantity) {
        List<CartItem> cartItems = cart.getCartItems();

        Optional<CartItem> cartItemOptional = cartItems.stream()
                .filter(p -> p.getProduct().equals(product))
                .findAny();
        if(cartItemOptional.isPresent()) {
            CartItem cartItem = cartItemOptional.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }
        else{
            cart.getCartItems().add(new CartItem(product, quantity));
        }
    }
}
