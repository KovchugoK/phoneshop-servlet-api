package com.es.phoneshop.web;

import com.es.phoneshop.model.ArrayListProductDao;
import com.es.phoneshop.model.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductDetaiilsPageServlet extends HttpServlet {
    ProductDao arrayListProductDao;

    @Override
    public void init() throws ServletException {
        super.init();
        arrayListProductDao = ArrayListProductDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        int index = request.getRequestURI().lastIndexOf("/");
        String idString = uri.substring(index + 1);
        request.setAttribute("product", arrayListProductDao.getProduct(Long.valueOf(idString)));
        request.getRequestDispatcher("/WEB-INF/pages/product.jsp").forward(request, response);
    }
}