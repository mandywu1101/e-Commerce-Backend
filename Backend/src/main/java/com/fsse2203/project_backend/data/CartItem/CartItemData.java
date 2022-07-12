package com.fsse2203.project_backend.data.CartItem;

import java.math.BigDecimal;

public class CartItemData {
    private Integer productId;
    private Integer cartQuantity;
    private String firebaseUId;

    public CartItemData(Integer productId, Integer cartQuantity, String firebaseUId) {
        this.productId = productId;
        this.cartQuantity = cartQuantity;
        this.firebaseUId = firebaseUId;

    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCartQuantity() {
        return cartQuantity;
    }

    public void setCartQuantity(Integer cartQuantity) {
        this.cartQuantity = cartQuantity;
    }

    public String getFirebaseUId() {
        return firebaseUId;
    }

    public void setFirebaseUId(String firebaseUId) {
        this.firebaseUId = firebaseUId;
    }

    @Override
    public String toString() {
        return "CartItemData{" +
                "productId=" + productId +
                ", cartQuantity=" + cartQuantity +
                ", firebaseUId='" + firebaseUId + '\'' +
                '}';
    }
}
