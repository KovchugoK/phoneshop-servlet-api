package com.es.phoneshop.model;

import java.util.ArrayList;
import java.util.List;

public class Compare {
    private List<CompareItem> compareItems = new ArrayList<>();

    public List<CompareItem> getCompareItems() {
        return compareItems;
    }

    public void setCompareItems(List<CompareItem> compareItems) {
        this.compareItems = compareItems;
    }
}
