package com.fsse2203.project_backend.data.Transaction.entity;

import com.fsse2203.project_backend.data.Transaction.data.Status;
import com.fsse2203.project_backend.data.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Transaction")
public class TransactionEntity {

    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private UserEntity user;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "total")
    private BigDecimal total;

    public TransactionEntity() {
    }

    public TransactionEntity(UserEntity userEntity, LocalDateTime dateTime, Status status, BigDecimal total) {
        this.user = userEntity;
        this.dateTime = dateTime;
        this.status = status;
        this.total = total;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
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

}
