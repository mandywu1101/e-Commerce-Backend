package com.fsse2203.project_backend.data.Transaction.data;

import com.fsse2203.project_backend.data.Transaction.entity.TransactionProductEntity;

import java.math.BigDecimal;


public class TransactionProductSnapshot {

    private Integer productId;

    private String name;

    private String description;

    private String imageUrl;

    private BigDecimal price;

    private Integer stock;

    public TransactionProductSnapshot(TransactionProductEntity transactionProductEntity) {
        this.productId = transactionProductEntity.getProductId();
        this.name = transactionProductEntity.getName();
        this.description = transactionProductEntity.getDescription();
        this.imageUrl = transactionProductEntity.getImageUrl();
        this.price = transactionProductEntity.getPrice();
        this.stock = transactionProductEntity.getStock();
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
