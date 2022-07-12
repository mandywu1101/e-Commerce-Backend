package com.fsse2203.project_backend.data.Product;

import com.fsse2203.project_backend.data.Product.dto.CreateProductDto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.math.BigDecimal;

public class CreateProductData {

    private String name;
    private String imageUrl;
    private BigDecimal price;
    private Integer stock;
    private String description;

    public CreateProductData(CreateProductDto createProductDto) {
        this.name = createProductDto.getName();
        this.imageUrl = createProductDto.getImageUrl();
        this.price = createProductDto.getPrice();
        this.stock = createProductDto.getStock();
        this.description = createProductDto.getDescription();
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