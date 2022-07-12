package com.fsse2203.project_backend.service;

import com.fsse2203.project_backend.data.Product.CreateProductData;
import com.fsse2203.project_backend.data.Product.ProductDetailsData;
import com.fsse2203.project_backend.data.Product.entity.ProductEntity;
import com.fsse2203.project_backend.exception.ProductNotFoundException;
import com.fsse2203.project_backend.exception.StockNotEnoughException;

import java.util.List;

public interface ProductService {
    ProductDetailsData createProduct(CreateProductData createProductData);
    List<ProductDetailsData> getAllProduct();
    ProductDetailsData findProductById(Integer product_id) throws ProductNotFoundException;
    ProductEntity findProductEntityById(Integer product_id) throws ProductNotFoundException;
    void deduceStock(Integer updatedStock, Integer productId) throws ProductNotFoundException, StockNotEnoughException;
    List<ProductDetailsData> sortPriceLowToHigh();
}
