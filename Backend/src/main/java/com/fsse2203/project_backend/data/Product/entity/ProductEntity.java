package com.fsse2203.project_backend.data.Product.entity;

import com.fsse2203.project_backend.data.Product.CreateProductData;
import com.google.type.Decimal;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @Column(name = "name" ,nullable = false)
    private String name;

    @Column(name = "image_url", nullable = true)
    private String imageUrl;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "description", nullable = true)
    private String description;

    public ProductEntity() {
    }

    public ProductEntity(CreateProductData createProductData) {
        this.name = createProductData.getName();
        this.imageUrl = createProductData.getImageUrl();
        this.price = createProductData.getPrice();
        this.stock = createProductData.getStock();
        this.description = createProductData.getDescription();
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
