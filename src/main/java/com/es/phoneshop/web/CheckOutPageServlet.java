package com.es.phoneshop.web;

import com.es.phoneshop.model.*;
import com.es.phoneshop.order.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class CheckOutPageServlet extends HttpServlet {
    private CartService cartService;
    private OrderService orderService;

    private static final String NAME_REGEX_EXPRESSION = "[A-Z][a-z]* +[A-Z][a-z]*";
    private static final String ADDRESS_REGEX_EXPRESSION = "[A-Z][a-z]* +([Ss]t(reet)?.?|[Ss]q(uare)?.?|[Pp]r(osp)?.?) +[1-9]\\d*-[1-9]\\d*";
    private static final String PHONE_REGEX_EXPRESSION = "\\+375\\d{9}";

    @Override
    public void init() throws ServletException {
        super.init();
        cartService = CartService.getInstance();
        orderService = OrderService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long totalSum = 0;
        Cart cart = cartService.getCart(request);
        List<CartItem> list = cart.getCartItems();
        for (int i = 0; i < list.size(); i++) {
            totalSum += list.get(i).getProduct().getPrice().intValue();
        }
        request.setAttribute("cart", cart);
        request.setAttribute("totalSum", totalSum);
        request.getRequestDispatcher("/WEB-INF/pages/checkout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Locale.setDefault(new Locale("en", "US"));
        Locale locale = request.getLocale();
        ResourceBundle res = ResourceBundle.getBundle("message", locale);
        boolean hasError = false;

        Cart cart = cartService.getCart(request);
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        orderService.placeOrder(cart, name, address, phone);
        if (!name.matches(NAME_REGEX_EXPRESSION)) {
            request.setAttribute("nameError", true);
            request.setAttribute("nameErrorMsg", res.getString("error.name"));
            hasError = true;
        }
        if (!address.matches(ADDRESS_REGEX_EXPRESSION)) {
            request.setAttribute("addressError", true);
            request.setAttribute("addressErrorMsg", res.getString("error.address"));
            hasError = true;
        }
        if (!phone.matches(PHONE_REGEX_EXPRESSION)) {
            request.setAttribute("phoneError", true);
            request.setAttribute("phoneErrorMsg", res.getString("error.phone"));
            hasError = true;
        }
        if (hasError) {
            doGet(request, response);
        } else {
            cartService.clearCart(cart);
            String id = orderService.getOrders().get(0).getOrderId();
            response.sendRedirect(request.getContextPath() + "/overview" + "?id=" + id);
        }
    }


}
