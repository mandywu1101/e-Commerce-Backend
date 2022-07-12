package com.fsse2203.project_backend.service.Impl;

import com.fsse2203.project_backend.data.CartItem.CartDetailsData;
import com.fsse2203.project_backend.data.CartItem.CartItemData;
import com.fsse2203.project_backend.data.CartItem.entity.CartItemEntity;
import com.fsse2203.project_backend.data.Product.entity.ProductEntity;
import com.fsse2203.project_backend.data.user.entity.UserEntity;
import com.fsse2203.project_backend.exception.CartItemNotFoundException;
import com.fsse2203.project_backend.exception.InvalidQuantityUpdateException;
import com.fsse2203.project_backend.exception.ProductNotFoundException;
import com.fsse2203.project_backend.exception.UserIdNotFoundException;
import com.fsse2203.project_backend.repository.CartItemRepository;
import com.fsse2203.project_backend.service.CartItemService;
import com.fsse2203.project_backend.service.ProductService;
import com.fsse2203.project_backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;
    private final UserService userService;
    private final ProductService productService;

    Logger logger = LoggerFactory.getLogger(CartItemServiceImpl.class);


    public CartItemServiceImpl(CartItemRepository cartItemRepository, UserService userService, ProductService productService) {
        this.cartItemRepository = cartItemRepository;
        this.userService = userService;
        this.productService = productService;
    }

    public boolean createCartItem(CartItemData cartItemData) throws ProductNotFoundException, UserIdNotFoundException {
        ProductEntity productEntity = productService.findProductEntityById(cartItemData.getProductId());
        if (productEntity == null) {
            throw new ProductNotFoundException();
        }
        logger.warn("Product: Found");


        UserEntity userEntity = userService.getUserEntity(cartItemData.getFirebaseUId());
        if (userEntity == null) {
            throw new UserIdNotFoundException();
        }
        logger.warn("User: Found");

        if (cartItemData != null) {
            if (cartItemData.getCartQuantity() == 0) {
                logger.warn("cant be added");
                return false;
            }
        }


        CartItemEntity searchedCartItem = cartItemRepository.findAllByProductProductIdAndUserUserID(
                productEntity.getProductId(), userEntity.getUserID());
        logger.warn("cartItemEntity: reached");
        if (searchedCartItem == null) {
            if (productEntity.getStock() >= cartItemData.getCartQuantity() &&
                    (productEntity.getStock() + cartItemData.getCartQuantity()) != 0) {
                CartItemEntity newCartItemEntity = new CartItemEntity(cartItemData, productEntity, userEntity);
                logger.warn("newCartItemEntity: created");
                cartItemRepository.save(newCartItemEntity);
                logger.warn("cartItemRepository: Saved");
                return true;
            }
        } else {
            if (searchedCartItem.getProduct().getStock() > cartItemData.getCartQuantity() &&
                    (searchedCartItem.getQuantity() + cartItemData.getCartQuantity()) != 0 &&
                    searchedCartItem.getQuantity() + cartItemData.getCartQuantity() <= productEntity.getStock()
            ) {
                Integer updateQuantity = searchedCartItem.getQuantity() + cartItemData.getCartQuantity();
                searchedCartItem.setQuantity(updateQuantity);
                cartItemRepository.save(searchedCartItem);
                return true;
            }
            return false;
        }
        return false;
    }

    public List<CartDetailsData> getAllCart(String firebaseUId) {
        List<CartDetailsData> cartDetailDataList = new ArrayList<>();
        List<CartItemEntity> cartItemEntityList = cartItemRepository.findAllByUserFirebaseUid(firebaseUId);
        logger.warn("User: Found!");

        for (CartItemEntity cartItemEntity : cartItemEntityList) {
            CartDetailsData cartDetailData = new CartDetailsData(cartItemEntity);
            cartDetailDataList.add(cartDetailData);
        }
        System.out.print(cartDetailDataList);
        return cartDetailDataList;

    }

    public List<CartItemEntity> getAllCartItemEntityByFirebaseUid(String firebaseUid) throws CartItemNotFoundException {
        List<CartItemEntity> cartItemEntityList = cartItemRepository.findAllByUserFirebaseUid(firebaseUid);
        if (cartItemEntityList.isEmpty()) {
            throw new CartItemNotFoundException();
        }
        return cartItemEntityList;
    }

    public CartDetailsData updateCart(Integer productId, Integer cartQuantity, String firebaseUid) throws ProductNotFoundException,
            InvalidQuantityUpdateException, CartItemNotFoundException {
        List<CartItemEntity> cartItemEntityList = cartItemRepository.findAllByUserFirebaseUid(firebaseUid);
        logger.warn("User: Found!");
        for (CartItemEntity cartItemEntity : cartItemEntityList) {
            logger.warn(productId.toString() + " " + cartItemEntity.getProduct().getProductId());
            if (cartItemEntity.getProduct().getProductId().equals(productId)) {
                if (cartQuantity == 0) {
                    logger.warn("InvalidQuantityUpdateException is threw");
                    throw new InvalidQuantityUpdateException();
                } else {
                    if (cartItemEntity.getProduct().getStock() >= cartQuantity) {
                        logger.warn("product quantity is correct");
                        cartItemEntity.setQuantity(cartQuantity);
                        cartItemRepository.save(cartItemEntity);
                        CartDetailsData cartDetailsData = new CartDetailsData(cartItemEntity);
                        return cartDetailsData;
                    }
                }
            }
        }
        logger.warn("CartItemNotFound is threw");
        throw new CartItemNotFoundException();
    }

    public CartDetailsData deleteCart(Integer productId, String firebaseUid) throws ProductNotFoundException {
        List<CartItemEntity> cartItemEntityList = cartItemRepository.findAllByUserFirebaseUid(firebaseUid);
        logger.warn("User: Found!");
        for (CartItemEntity cartItemEntity : cartItemEntityList) {
            if (cartItemEntity.getProduct().getProductId() == productId) {
                CartDetailsData cartDetailsData = new CartDetailsData(cartItemEntity);
                cartItemRepository.delete(cartItemEntity);
                return cartDetailsData;
            }
        }
        throw new ProductNotFoundException();
    }

    public void emptyCart(Integer productId, Integer UserId) {
        CartItemEntity cartItemEntity = cartItemRepository.findAllByProductProductIdAndUserUserID(
                productId, UserId);
        cartItemRepository.delete(cartItemEntity);
    }


}






