package com.es.phoneshop.model;

public class CartItem {
    private Product product;
    private int quantity = 1;

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public void setProduct(Product product) {
        this.product = product;

    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
