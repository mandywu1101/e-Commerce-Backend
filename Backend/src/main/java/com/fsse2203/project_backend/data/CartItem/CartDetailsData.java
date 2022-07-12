package com.fsse2203.project_backend.data.CartItem;

import com.fsse2203.project_backend.data.CartItem.entity.CartItemEntity;

import java.math.BigDecimal;

public class CartDetailsData {

    private Integer productId;
    private String name;
    private String imageUrl;
    private BigDecimal price;
    private Integer stock;
    private Integer cartQuantity;

    public CartDetailsData(CartItemEntity cartItemEntity) {
        this.productId = cartItemEntity.getProduct().getProductId();
        this.name = cartItemEntity.getProduct().getName();
        this.imageUrl =  cartItemEntity.getProduct().getImageUrl();
        this.price =  cartItemEntity.getProduct().getPrice();
        this.stock =  cartItemEntity.getProduct().getStock();
        this.cartQuantity = cartItemEntity.getQuantity();
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

    public Integer getCartQuantity() {
        return cartQuantity;
    }

    public void setCartQuantity(Integer cartQuantity) {
        this.cartQuantity = cartQuantity;
    }

    @Override
    public String toString() {
        return "CartDetailsData{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", cartQuantity=" + cartQuantity +
                '}';
    }
}
