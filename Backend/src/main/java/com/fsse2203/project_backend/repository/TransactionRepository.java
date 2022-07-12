package com.fsse2203.project_backend.repository;

import com.fsse2203.project_backend.data.Transaction.entity.TransactionEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<TransactionEntity, Integer> {
    TransactionEntity findAllByTransactionIdAndUserUserID(Integer transactionId, Integer userId);
    List<TransactionEntity> findAllByUserUserID(Integer userID);
}
