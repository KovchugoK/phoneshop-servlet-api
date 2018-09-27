package com.es.phoneshop.web;

import com.es.phoneshop.model.ArrayListProductDao;
import com.es.phoneshop.model.Product;
import com.es.phoneshop.model.ProductDao;

import java.net.ConnectException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class ProductListServlet extends HttpServlet {
    private ProductDao productDao;


    @Override
    public void init() throws ServletException {
        super.init();
        productDao = ArrayListProductDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("products", productDao.findProducts(request));
        request.getRequestDispatcher("/WEB-INF/pages/productList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Locale.setDefault(new Locale("en", "US"));
        Locale locale = request.getLocale();
        ResourceBundle res = ResourceBundle.getBundle("message", locale);

        HttpSession session = request.getSession();
        List<Product> products = productDao.findProducts(request);
        List<Product> suitableProduct = new ArrayList<>();
        String searchText = request.getParameter("search");
        searchText.toLowerCase();
        String productCode;
        String productDescription;
        for (Product product : products) {
            productCode = product.getCode().toLowerCase();
            productDescription = product.getDescription().toLowerCase();
            if (productCode.contains(searchText) || productDescription.contains(searchText)) {
                suitableProduct.add(product);
            }
        }
        if (!suitableProduct.isEmpty()) {
            request.removeAttribute("product");
            session.setAttribute("product", suitableProduct);
            response.sendRedirect(request.getRequestURI() + "?countOfSearchProd=" + suitableProduct.size());

        } else {
            request.setAttribute("error", true);
            request.setAttribute("errorMsg", res.getString("error.search"));
            doGet(request, response);
        }

    }

}
