package com.es.phoneshop.web;

import com.es.phoneshop.model.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.ResourceBundle;

public class CartPageServlet extends HttpServlet {
    private CartService cartService;
    private ArrayListProductDao productDao;

    @Override
    public void init() throws ServletException {
        super.init();
        cartService = CartService.getInstance();
        productDao = ArrayListProductDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        showPage(cartService.getCart(request), request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean hasErrors = false;
        String[] productIds = request.getParameterValues("productId");
        String[] quantities = request.getParameterValues("quantity");
        String[] errors = new String[productIds.length];
        String deleteValue = request.getParameter("delete");
        Locale.setDefault(new Locale("en", "US"));
        Locale locale = request.getLocale();
        ResourceBundle res = ResourceBundle.getBundle("message", locale);
        Cart cart = cartService.getCart(request);
        int quantity;
        Product product;

        if (deleteValue != null) {
            int deleteProductId = Integer.valueOf(deleteValue);
            product = cartService.getCart(request).getCartItems().get(deleteProductId).getProduct();
            request.setAttribute("sucsess", true);
            request.setAttribute("sucsessMsg", res.getString("sucsess.msg"));
            cartService.deleteProduct(cart, product, deleteProductId);
        } else {
            for (int i = 0; i < productIds.length; i++) {
                product = productDao.getProduct(Long.valueOf(productIds[i]));
                try {
                    quantity = DecimalFormat.getInstance(locale).parse(quantities[i]).intValue();
                    if (quantity < 0) {
                        throw new IllegalArgumentException();
                    }
                    cartService.update(cart, product, quantity);
                } catch (ParseException e) {
                    errors[i] = res.getString("error.number.format");
                    hasErrors = true;
                } catch (IllegalArgumentException e) {
                    errors[i] = res.getString("error.number.less.then.zero");
                    hasErrors = true;
                }
            }
        }
        if (hasErrors) {
            request.setAttribute("quantities", quantities);
            request.setAttribute("error", true);
            request.setAttribute("errors", errors);
            showPage(cart, request, response);
        } else {
            doGet(request, response);
        }
    }

    private void showPage(Cart cart, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("cart", cart);
        request.getRequestDispatcher("/WEB-INF/pages/cart.jsp").forward(request, response);
    }
}
