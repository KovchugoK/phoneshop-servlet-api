package com.es.phoneshop.web;

import com.es.phoneshop.model.*;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.ResourceBundle;

public class ProductDetaiilsPageServlet extends HttpServlet {
    private ProductDao productDao;
    private CartService cartService;

    @Override
    public void init() throws ServletException {
        super.init();
        productDao = ArrayListProductDao.getInstance();
        cartService = CartService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long productId = Long.valueOf(getLastPathParameter(request));
        Product product = productDao.getProduct(productId);
        showPage(product, request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long productId = Long.valueOf(getLastPathParameter(request));
        Product product = productDao.getProduct(productId);
        Integer quantity;
        Locale locale = request.getLocale();
        ResourceBundle res = ResourceBundle.getBundle("messages",locale);
        try {
            quantity = DecimalFormat.getInstance(locale).parse(request.getParameter("quantity")).intValue();
            if (quantity < 0) {
                throw new IllegalArgumentException();
            }
        } catch (ParseException e) {
            exeptionCase(product, request, response, res.getString("error.number.format"));
            return;
        } catch (IllegalArgumentException e) {
            exeptionCase(product, request, response, "Number must being > 0");
            return;
        }
        Cart cart = cartService.getCart(request);
        cartService.add(cart, product, quantity);

        request.setAttribute("addQuantity", quantity);
        //showPage(product, request, response);
        response.sendRedirect(request.getRequestURI() + "?addQuantity=" + quantity );
    }

    private void showPage(Product product, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("product", product);
        request.getRequestDispatcher("/WEB-INF/pages/product.jsp").forward(request, response);
    }

    private void exeptionCase(Product product, HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("error", Boolean.TRUE);
        request.setAttribute("errorText", message);
        showPage(product, request, response);
    }

    private String getLastPathParameter(HttpServletRequest request) {
        String uri = request.getRequestURI();
        int index = request.getRequestURI().lastIndexOf("/");
        return uri.substring(index + 1);
    }
}