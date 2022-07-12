package com.fsse2203.project_backend.data.Transaction.data;

import com.fsse2203.project_backend.data.Transaction.entity.TransactionEntity;
import com.fsse2203.project_backend.data.Transaction.entity.TransactionProductEntity;

import javax.persistence.*;
import java.math.BigDecimal;

public class TransactionProductDetailedData {
    private Integer transactionProductId;
    private TransactionProductSnapshot transactionProductSnapshot;
    private Integer quantity;
    private BigDecimal subtotal;

    public TransactionProductDetailedData(TransactionProductEntity transactionProductEntity,
                                          TransactionProductSnapshot transactionProductSnapshot) {
        this.transactionProductId = transactionProductEntity.getTransactionProductId();
        this.transactionProductSnapshot = transactionProductSnapshot;
        this.quantity = transactionProductEntity.getQuantity();
        this.subtotal = transactionProductEntity.getSubtotal();
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
