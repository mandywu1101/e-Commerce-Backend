package com.fsse2203.project_backend.data.Transaction.entity;

import com.fsse2203.project_backend.data.CartItem.CartDetailsData;
import com.fsse2203.project_backend.data.CartItem.entity.CartItemEntity;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;

@Entity
@Table(name = "Tranaction_Product")
public class TransactionProductEntity {

    @Id
    @Column(name = "transaction_product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionProductId;

    @Column(name = "productId")
    private Integer productId;

    @Column(name = "name" ,nullable = false)
    private String name;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "image_url", nullable = true)
    private String imageUrl;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "transaction_id", nullable = false)
    private TransactionEntity transactionEntity;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "subtotal")
    private BigDecimal subtotal;

    public TransactionProductEntity() {
    }

    public TransactionProductEntity(CartItemEntity cartItemEntity, TransactionEntity transactionEntity) {
        this.productId = cartItemEntity.getProduct().getProductId();
        this.name = cartItemEntity.getProduct().getName();
        this.description = cartItemEntity.getProduct().getDescription();
        this.imageUrl = cartItemEntity.getProduct().getImageUrl();
        this.price = cartItemEntity.getProduct().getPrice();
        this.stock = cartItemEntity.getProduct().getStock();
        this.quantity = cartItemEntity.getQuantity();
        this.subtotal = cartItemEntity.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItemEntity.getQuantity()));
        this.transactionEntity = transactionEntity;
    }

    public Integer getTransactionProductId() {
        return transactionProductId;
    }

    public void setTransactionProductId(Integer transactionProductId) {
        this.transactionProductId = transactionProductId;
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

    public TransactionEntity getTransactionEntity() {
        return transactionEntity;
    }

    public void setTransactionEntity(TransactionEntity transactionEntity) {
        this.transactionEntity = transactionEntity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "TransactionProductEntity{" +
                "transactionProductId=" + transactionProductId +
                ", productId=" + productId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", transactionEntity=" + transactionEntity +
                ", quantity=" + quantity +
                ", subtotal=" + subtotal +
                '}';
    }
}
