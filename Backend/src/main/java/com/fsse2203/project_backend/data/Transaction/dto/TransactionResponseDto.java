package com.fsse2203.project_backend.data.Transaction.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsse2203.project_backend.data.Transaction.data.Status;
import com.fsse2203.project_backend.data.Transaction.data.TransactionDetailedData;
import com.fsse2203.project_backend.data.Transaction.data.TransactionProductDetailedData;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionResponseDto {

    @JsonProperty("transaction_id")
    private Integer transactionId;

    @JsonProperty("user_id")
    private Integer UserId;

    @JsonProperty("date_time")
    @JsonFormat(pattern = "yyyyMMdd'T'HH:mm:ss")
    private LocalDateTime dateTime;

    @JsonProperty("status")
    private Status status;

    @JsonProperty("total")
    private BigDecimal total;

    @JsonProperty("item")
    private List<TransactionProductDto> transactionProductDtoList;

    public TransactionResponseDto(TransactionDetailedData transactionDetailedData) {
        this.transactionId = transactionDetailedData.getTransactionId();
        this.UserId = transactionDetailedData.getUserId();
        this.dateTime = transactionDetailedData.getDateTime();
        this.status = transactionDetailedData.getStatus();
        this.total = transactionDetailedData.getTotal();
        setTransactionProductDtoList(transactionDetailedData.getTransactionProductDetailedDataList());
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
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

    public List<TransactionProductDto> getTransactionProductDtoList() {
        return transactionProductDtoList;
    }

    public void setTransactionProductDtoList(List<TransactionProductDetailedData> transactionProductDetailedDataList) {
        List<TransactionProductDto> transactionProductDtoList = new ArrayList<>();
        for (TransactionProductDetailedData transactionProductDetailedData : transactionProductDetailedDataList) {
            TransactionProductDto transactionProductDto = new TransactionProductDto(transactionProductDetailedData);
            transactionProductDtoList.add(transactionProductDto);
        }
        this.transactionProductDtoList = transactionProductDtoList;
    }
}
