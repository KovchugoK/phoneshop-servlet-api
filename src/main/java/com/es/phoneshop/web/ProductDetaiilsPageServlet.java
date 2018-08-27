package com.es.phoneshop.web;

import com.es.phoneshop.model.ArrayListProductDao;
import com.es.phoneshop.model.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductDetaiilsPageServlet extends HttpServlet {
    private ProductDao productDao;

    @Override
    public void init() throws ServletException {
        super.init();
        productDao = ArrayListProductDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("product", productDao.getProduct(Long.valueOf(getLastPathParameter(request))));
        request.getRequestDispatcher("/WEB-INF/pages/product.jsp").forward(request, response);
    }

    private String getLastPathParameter(HttpServletRequest request) {
        String uri = request.getRequestURI();
        int index = request.getRequestURI().lastIndexOf("/");
        return uri.substring(index + 1);
    }
}