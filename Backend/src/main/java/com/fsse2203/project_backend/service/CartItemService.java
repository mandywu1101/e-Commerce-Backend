package com.fsse2203.project_backend.service;

import com.fsse2203.project_backend.data.CartItem.CartDetailsData;
import com.fsse2203.project_backend.data.CartItem.CartItemData;
import com.fsse2203.project_backend.data.CartItem.entity.CartItemEntity;
import com.fsse2203.project_backend.exception.CartItemNotFoundException;
import com.fsse2203.project_backend.exception.InvalidQuantityUpdateException;
import com.fsse2203.project_backend.exception.ProductNotFoundException;
import com.fsse2203.project_backend.exception.UserIdNotFoundException;

import java.util.List;

public interface CartItemService {
    boolean createCartItem(CartItemData cartItemData) throws ProductNotFoundException, UserIdNotFoundException;
    List<CartDetailsData> getAllCart(String firebaseUId);
    CartDetailsData updateCart(Integer productId, Integer cartQuantity, String firebaseUid) throws ProductNotFoundException, InvalidQuantityUpdateException, CartItemNotFoundException;
    CartDetailsData deleteCart(Integer productId, String firebaseUid) throws ProductNotFoundException;
    List<CartItemEntity> getAllCartItemEntityByFirebaseUid(String firebaseUid) throws CartItemNotFoundException;
    void emptyCart(Integer productId, Integer UserId);
}
