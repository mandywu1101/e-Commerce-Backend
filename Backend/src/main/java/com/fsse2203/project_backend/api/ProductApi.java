package com.fsse2203.project_backend.api;

import com.fsse2203.project_backend.config.EnvConfig;
import com.fsse2203.project_backend.data.Product.CreateProductData;
import com.fsse2203.project_backend.data.Product.ProductDetailsData;
import com.fsse2203.project_backend.data.Product.dto.CreateProductDto;
import com.fsse2203.project_backend.data.Product.dto.ProductDetailsDto;
import com.fsse2203.project_backend.data.Product.dto.ProductGetAllRequestDto;
import com.fsse2203.project_backend.exception.ProductNotFoundException;
import com.fsse2203.project_backend.service.ProductService;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = {EnvConfig.devBaseUrl, EnvConfig.prodBaseUrl}, maxAge = 3600)
@RestController
public class ProductApi {
    private final ProductService productService;

    public ProductApi(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/product")
    public ProductDetailsDto loginForCreateProduct(@RequestBody CreateProductDto createProductDto) {
        CreateProductData createProductData = new CreateProductData(createProductDto);
        ProductDetailsDto productDetailsDto = new ProductDetailsDto(
                productService.createProduct(createProductData));
        return productDetailsDto;
    }

    @GetMapping("/public/product")
    public List<ProductGetAllRequestDto> getAllProduct() {
        List<ProductGetAllRequestDto> productGetAllRequestDtoList = new ArrayList<>();
        List<ProductDetailsData> productDetailsDataList = productService.getAllProduct();
        for (ProductDetailsData productDetailsData : productDetailsDataList) {
            ProductGetAllRequestDto productGetAllRequestDto = new ProductGetAllRequestDto(productDetailsData);
            productGetAllRequestDtoList.add(productGetAllRequestDto);
        }
        return productGetAllRequestDtoList;
    }

    @GetMapping("/public/product/{product_id}")
    public ProductDetailsDto findProductById(@PathVariable Integer product_id) throws ProductNotFoundException {
        ProductDetailsDto productDetailsDto = new ProductDetailsDto(productService.findProductById(product_id));
        return productDetailsDto;
    }

    @GetMapping("/public/product/priceLowToHigh")
    public List<ProductGetAllRequestDto> getAllProductSortByPriceLowToHigh() {
        List<ProductGetAllRequestDto> productGetAllRequestDtoList = new ArrayList<>();
        List<ProductDetailsData> productDetailsDataList = productService.sortPriceLowToHigh();
        for (ProductDetailsData productDetailsData : productDetailsDataList) {
            ProductGetAllRequestDto productGetAllRequestDto = new ProductGetAllRequestDto(productDetailsData);
            productGetAllRequestDtoList.add(productGetAllRequestDto);
        }
        return productGetAllRequestDtoList;
    }
}
