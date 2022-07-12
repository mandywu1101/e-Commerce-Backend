package com.fsse2203.project_backend.service.Impl;

import com.fsse2203.project_backend.data.Product.CreateProductData;
import com.fsse2203.project_backend.data.Product.ProductDetailsData;
import com.fsse2203.project_backend.data.Product.entity.ProductEntity;
import com.fsse2203.project_backend.exception.ProductNotFoundException;
import com.fsse2203.project_backend.exception.StockNotEnoughException;
import com.fsse2203.project_backend.repository.ProductRepository;
import com.fsse2203.project_backend.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDetailsData createProduct(CreateProductData createProductData) {
        ProductEntity productEntity = new ProductEntity(createProductData);
        productRepository.save(productEntity);
        ProductDetailsData productDetailsData = new ProductDetailsData(productEntity);
        return productDetailsData;
    }

    public List<ProductDetailsData> getAllProduct() {
        List<ProductDetailsData> productDetailsDataList = new ArrayList<>();
        for (ProductEntity productEntity : productRepository.findAll()) {
            productDetailsDataList.add(new ProductDetailsData(productEntity));
        }
        return productDetailsDataList;
    }

    public List<ProductDetailsData> sortPriceLowToHigh() {
        List<ProductDetailsData> productDetailsDataList = new ArrayList<>();
        for (ProductEntity productEntity : productRepository.findAllByOrderByPriceAsc()){
            productDetailsDataList.add(new ProductDetailsData(productEntity));
        }
        return productDetailsDataList;
    }

    public ProductDetailsData findProductById(Integer product_id) throws ProductNotFoundException {
        ProductEntity productEntity = productRepository.findProductEntityByProductId(product_id);
        if (productEntity != null) {
            ProductDetailsData productDetailsData = new ProductDetailsData(productEntity);
            return productDetailsData;
        } else {
            logger.warn("Product: Product Not Found");
            throw new ProductNotFoundException();
        }
    }

    public ProductEntity findProductEntityById(Integer product_id) throws ProductNotFoundException {
        for (ProductEntity productEntity : productRepository.findAll()) {
            if (product_id == productEntity.getProductId()) {
                return productEntity;
            }
        }
        logger.warn("Product: Product Not Found");
        throw new ProductNotFoundException();
    }

    public void deduceStock(Integer soldQuantity, Integer productId) throws ProductNotFoundException, StockNotEnoughException {
        ProductEntity productEntity = findProductEntityById(productId);
        if (soldQuantity > productEntity.getStock()){
            throw new StockNotEnoughException();
        }
        productEntity.setStock(productEntity.getStock() - soldQuantity);
        productRepository.save(productEntity);
    }

}