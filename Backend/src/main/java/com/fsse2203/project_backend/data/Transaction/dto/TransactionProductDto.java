package com.fsse2203.project_backend.data.Transaction.dto;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsse2203.project_backend.data.Transaction.data.TransactionProductDetailedData;
import com.fsse2203.project_backend.data.Transaction.data.TransactionProductSnapshot;
import com.fsse2203.project_backend.data.Transaction.entity.TransactionEntity;

import java.math.BigDecimal;

public class TransactionProductDto {

    @JsonProperty("transaction_product")
    private Integer transactionProductId;

    @JsonProperty("items")
    private TransactionProductSnapshot transactionProductSnapshot;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("subtotal")
    private BigDecimal subtotal;

    public TransactionProductDto(TransactionProductDetailedData transactionProductDetailedData) {
        this.transactionProductId = transactionProductDetailedData.getTransactionProductId();
        this.quantity = transactionProductDetailedData.getQuantity();
        this.subtotal = transactionProductDetailedData.getSubtotal();
        setTransactionProductSnapshot(transactionProductDetailedData.getTransactionProductSnapshot());
    }

    public Integer getTransactionProductId() {
        return transactionProductId;
    }

    public void setTransactionProductId(Integer transactionProductId) {
        this.transactionProductId = transactionProductId;
    }

    public TransactionProductSnapshot getTransactionProductSnapshot() {
        return transactionProductSnapshot;
    }

    public void setTransactionProductSnapshot(TransactionProductSnapshot transactionProductSnapshot) {
        this.transactionProductSnapshot = transactionProductSnapshot;
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
}
