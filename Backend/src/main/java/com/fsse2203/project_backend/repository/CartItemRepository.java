package com.fsse2203.project_backend.repository;


import com.fsse2203.project_backend.data.CartItem.entity.CartItemEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartItemRepository extends CrudRepository<CartItemEntity, Integer> {
    CartItemEntity findAllByProductProductIdAndUserUserID(Integer productId, Integer UserId);
    List<CartItemEntity> findAllByUserFirebaseUid(String firebaseUid);
}
