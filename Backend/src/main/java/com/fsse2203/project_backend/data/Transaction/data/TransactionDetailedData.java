package com.fsse2203.project_backend.data.Transaction.data;

import com.fsse2203.project_backend.data.Transaction.entity.TransactionEntity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class TransactionDetailedData {
    private Integer transactionId;

    private Integer userId;

    private LocalDateTime dateTime;

    private Status status;

    private BigDecimal total;

    private List<TransactionProductDetailedData> transactionProductDetailedDataList;

    public TransactionDetailedData(TransactionEntity transactionEntity,
                                   List<TransactionProductDetailedData> transactionProductDetailedDataList) {
        this.transactionId = transactionEntity.getTransactionId();
        this.userId = transactionEntity.getUser().getUserID();
        this.dateTime = transactionEntity.getDateTime();
        this.status = transactionEntity.getStatus();
        this.total = transactionEntity.getTotal();
        this.transactionProductDetailedDataList = transactionProductDetailedDataList;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        userId = userId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<TransactionProductDetailedData> getTransactionProductDetailedDataList() {
        return transactionProductDetailedDataList;
    }

    public void setTransactionProductDetailedDataList(List<TransactionProductDetailedData> transactionProductDetailedDataList) {
        this.transactionProductDetailedDataList = transactionProductDetailedDataList;
    }
}
