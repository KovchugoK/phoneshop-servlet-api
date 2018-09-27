package com.es.phoneshop.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompareServise {
    private static final String CART_ATTRIBUTE_NAME = "cart";
    private static Object lock = new Object();
    private static volatile CompareServise compareServise = null;


    public static CompareServise getInstance() {
        if (compareServise == null) {
            synchronized (lock) {
                if (compareServise == null) {
                    compareServise = new CompareServise();
                }
            }
        }
        return compareServise;
    }

    private CompareServise() {

    }

    public Compare get(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Compare compare = (Compare) session.getAttribute("compare");
        if (compare == null) {
            compare = new Compare();
            session.setAttribute("compare", compare);
        }
        return compare;
    }

    public void add(Compare compare, Product product) {
        compare.getCompareItems().add(new CompareItem(product));
    }
}

