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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckOutPageServlet extends HttpServlet {
    private CartService cartService;
    private OrderService orderService;
    private long totalSum;


    @Override
    public void init() throws ServletException {
        super.init();
        cartService = CartService.getInstance();
        orderService = OrderService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        totalSum = 0;
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
        if (!checkWithRegExp(name, "[A-Z][a-z]* +[A-Z][a-z]*")) {
            request.setAttribute("nameError", true);
            request.setAttribute("nameErrorMsg", res.getString("error.name"));
            hasError = true;
        }
        if (!checkWithRegExp(address, "[A-Z][a-z]* +([Ss]t(reet)?.?|[Ss]q(uare)?.?|[Pp]r(osp)?.?) +[1-9]\\d*-[1-9]\\d*")) {
            request.setAttribute("adresError", true);
            request.setAttribute("adresErrorMsg", res.getString("error.adress"));
            hasError = true;
        }
        if (!checkWithRegExp(phone, "\\+375\\d{9}")) {
            request.setAttribute("phoneError", true);
            request.setAttribute("phoneErrorMsg", res.getString("error.phone"));
            hasError = true;
        }
        if (hasError) {
            doGet(request, response);
        } else {
                cartService.deleteCart(cart);
                String id = orderService.getOrders().get(0).getOrderId();
                response.sendRedirect(request.getContextPath() + "/overview" + "?id=" + id);
        }
    }


    private static boolean checkWithRegExp(String userNameString, String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(userNameString);
        return m.matches();
    }
}
