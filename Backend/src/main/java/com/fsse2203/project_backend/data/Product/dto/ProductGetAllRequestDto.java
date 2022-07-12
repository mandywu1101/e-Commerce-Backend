package com.fsse2203.project_backend.data.Product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsse2203.project_backend.data.Product.ProductDetailsData;

import java.math.BigDecimal;

public class ProductGetAllRequestDto {
    @JsonProperty("product_id")
    private Integer productId;

    @JsonProperty("product_name")
    private String name;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("hasStock")
    private boolean stock;



    public ProductGetAllRequestDto(ProductDetailsData productDetailsData) {
        this.productId = productDetailsData.getProductId();
        this.name = productDetailsData.getName() ;
        this.price = productDetailsData.getPrice();
        this.imageUrl = productDetailsData.getImageUrl();
        setStock(productDetailsData.getStock());
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setStock(Integer stock) {
        if (stock != 0){
            this.stock = true;
        }
        else{
            this.stock = false;
        }
    }
}
