package com.fsse2203.project_backend.data.CartItem.entity;

import com.fsse2203.project_backend.data.CartItem.CartItemData;
import com.fsse2203.project_backend.data.Product.entity.ProductEntity;
import com.fsse2203.project_backend.data.user.UserDetailsData;
import com.fsse2203.project_backend.data.user.entity.UserEntity;

import javax.persistence.*;

@Entity
@Table(name = "cart_item")
public class CartItemEntity {
    @Id
    @Column(name = "cart_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cart_item_id;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "product", nullable = false)
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "user", nullable = false)
    private UserEntity user;

    public CartItemEntity() {
    }

    public CartItemEntity(CartItemData cartItemData, ProductEntity product, UserEntity user) {
        this.quantity = cartItemData.getCartQuantity();
        this.product = product;
        this.user = user;
    }

    public Integer getCart_item_id() {
        return cart_item_id;
    }

    public void setCart_item_id(Integer cart_item_id) {
        this.cart_item_id = cart_item_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
