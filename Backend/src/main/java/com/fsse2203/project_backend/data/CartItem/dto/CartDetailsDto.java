package com.fsse2203.project_backend.data.CartItem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsse2203.project_backend.data.CartItem.CartDetailsData;
import com.fsse2203.project_backend.data.CartItem.CartItemData;
import com.fsse2203.project_backend.data.CartItem.entity.CartItemEntity;

import java.math.BigDecimal;

public class CartDetailsDto {

    @JsonProperty("product_id")
    private Integer productId;

    @JsonProperty("product_name")
    private String name;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("cart_quantity")
    private Integer cartQuantity;

    @JsonProperty("stock")
    private Integer stock;

    public CartDetailsDto(CartDetailsData cartDetailsData) {
        this.productId = cartDetailsData.getProductId();
        this.name = cartDetailsData.getName();
        this.imageUrl =  cartDetailsData.getImageUrl();
        this.price =  cartDetailsData.getPrice();
        this.cartQuantity = cartDetailsData.getCartQuantity();
        this.stock =  cartDetailsData.getStock();
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

    public Integer getCartQuantity() {
        return cartQuantity;
    }

    public void setCartQuantity(Integer cartQuantity) {
        this.cartQuantity = cartQuantity;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
