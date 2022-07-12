package com.fsse2203.project_backend.data.Product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsse2203.project_backend.data.Product.ProductDetailsData;

import java.math.BigDecimal;

public class ProductDetailsDto {
    @JsonProperty("product_id")
    private Integer productId;

    @JsonProperty("product_name")
    private String name;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("stock")
    private Integer stock;

    @JsonProperty("description")
    private String description;

    public ProductDetailsDto(ProductDetailsData productDetailsData) {
        this.productId = productDetailsData.getProductId();
        this.name = productDetailsData.getName();
        this.imageUrl = productDetailsData.getImageUrl();
        this.price = productDetailsData.getPrice();
        this.stock = productDetailsData.getStock();
        this.description = productDetailsData.getDescription();
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
