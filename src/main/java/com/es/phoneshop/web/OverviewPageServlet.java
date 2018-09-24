package com.es.phoneshop.web;

import com.es.phoneshop.model.ArrayListProductDao;
import com.es.phoneshop.model.ProductDao;
import com.es.phoneshop.order.Order;
import com.es.phoneshop.order.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OverviewPageServlet extends HttpServlet {

    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        super.init();
        orderService = OrderService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Order order = orderService.getOrder(id);
        request.setAttribute("order", order);
        request.getRequestDispatcher("/WEB-INF/pages/overview.jsp").forward(request, response);
    }
}
