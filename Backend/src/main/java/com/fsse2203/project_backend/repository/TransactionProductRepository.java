package com.fsse2203.project_backend.repository;

import com.fsse2203.project_backend.data.Transaction.entity.TransactionProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionProductRepository extends CrudRepository<TransactionProductEntity, Integer> {
    List<TransactionProductEntity> findAllByTransactionEntityTransactionId(Integer transactionId);
}
