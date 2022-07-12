package com.fsse2203.project_backend.repository;

import com.fsse2203.project_backend.data.Product.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
    ProductEntity findProductEntityByProductId(Integer product_id);
    List<ProductEntity> findAllByOrderByPriceAsc();
}
