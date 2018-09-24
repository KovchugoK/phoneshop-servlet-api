package com.es.phoneshop.web;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.Time;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DosFilter implements Filter {
    private Map<String, Integer> requestCountMap = Collections.synchronizedMap(new HashMap<>());

    private static final int INTERVAL = 3;
    private static final int MAX_COUNT_OF_REQUEST = 20;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Fil " + request.getRemoteAddr());
        long startTime = System.currentTimeMillis();
        String address = request.getRemoteAddr();
        Integer count = requestCountMap.get(address);
        if (count == null) {
            count = 1;
        } else {
            count += 1;
        }
        requestCountMap.put(address, count);

        if (count < MAX_COUNT_OF_REQUEST &&  System.currentTimeMillis() - INTERVAL > startTime) {
            ((HttpServletResponse) response).sendError(429);

        } else {
            count = 0;
            startTime = System.currentTimeMillis();
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
