package com.es.phoneshop.model;

import java.math.BigDecimal;
import java.util.Currency;

public class Product {
    private Long id;
    private String code;
    private String description;
    private BigDecimal price;
    private Currency currency;
    private Integer stock;

    public Product() {
    }

    public Product(Long id) {
        this.id = id;
    }

    public Product(Long id, String code, String description, BigDecimal price, Integer stock) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
