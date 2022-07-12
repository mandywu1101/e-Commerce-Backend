package com.fsse2203.project_backend.data.Product;

import com.fsse2203.project_backend.data.Product.entity.ProductEntity;

import java.math.BigDecimal;

public class ProductDetailsData {

    private Integer productId;
    private String name;
    private String imageUrl;
    private BigDecimal price;
    private Integer stock;
    private String description;

    public ProductDetailsData(ProductEntity productEntity) {
        this.productId = productEntity.getProductId();
        this.name = productEntity.getName();
        this.imageUrl = productEntity.getImageUrl();
        this.price = productEntity.getPrice();
        this.stock = productEntity.getStock();
        this.description = productEntity.getDescription();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
