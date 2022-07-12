package com.fsse2203.project_backend.api;

import com.fsse2203.project_backend.config.EnvConfig;
import com.fsse2203.project_backend.data.CartItem.CartDetailsData;
import com.fsse2203.project_backend.data.CartItem.CartItemData;
import com.fsse2203.project_backend.data.CartItem.dto.CartDetailsDto;
import com.fsse2203.project_backend.data.CartItem.dto.CartStatusDto;
import com.fsse2203.project_backend.exception.CartItemNotFoundException;
import com.fsse2203.project_backend.exception.InvalidQuantityUpdateException;
import com.fsse2203.project_backend.exception.ProductNotFoundException;
import com.fsse2203.project_backend.exception.UserIdNotFoundException;
import com.fsse2203.project_backend.service.CartItemService;
import com.fsse2203.project_backend.service.Impl.CartItemServiceImpl;
import com.fsse2203.project_backend.util.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = {EnvConfig.devBaseUrl, EnvConfig.prodBaseUrl}, maxAge = 3600)
@RestController
public class CartApi {

    private final CartItemService cartItemService;

    public CartApi(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    Logger logger = LoggerFactory.getLogger(CartItemServiceImpl.class);

    @PutMapping("/cart/add-item/{pid}/{quantity}")
    public CartStatusDto addCart(@PathVariable(value = "pid") Integer productId,
                                 @PathVariable(value = "quantity") Integer quantity, Authentication authentication) throws
            ProductNotFoundException, UserIdNotFoundException {

//        find the firebase ID
        String firebaseUId = SecurityUtil.getUidfromFirebase(authentication);
        CartItemData cartItemData = new CartItemData(productId, quantity, firebaseUId);

        logger.warn("New cart Item is created");
        System.out.println(cartItemData);

        CartStatusDto cartStatusDto = new CartStatusDto(cartItemService.createCartItem(cartItemData));
        return cartStatusDto;
    }

    @GetMapping("/cart")
    public List<CartDetailsDto> getCart(Authentication authentication) {
        String firebaseUId = SecurityUtil.getUidfromFirebase(authentication);
        List<CartDetailsDto> cartDetailsDtoList = new ArrayList<>();
        for (CartDetailsData cartDetailsData : cartItemService.getAllCart(firebaseUId)) {
            CartDetailsDto cartDetailsDto = new CartDetailsDto(cartDetailsData);
            cartDetailsDtoList.add(cartDetailsDto);
        }
        return cartDetailsDtoList;
    }

    @PatchMapping("/cart/{pid}/{quantity}")
    public CartDetailsDto updateCartQuantity(@PathVariable(value = "pid") Integer productId,
                                             @PathVariable(value = "quantity") Integer newCartQuantity,
                                             Authentication authentication) throws ProductNotFoundException, InvalidQuantityUpdateException, CartItemNotFoundException {
        String firebaseUId = SecurityUtil.getUidfromFirebase(authentication);
        CartDetailsDto cartDetailsDto = new CartDetailsDto(
                cartItemService.updateCart(productId, newCartQuantity, firebaseUId));
        return cartDetailsDto;
        // test
    }

    @DeleteMapping("/cart/{pid}")
    public CartDetailsDto deleteCart(@PathVariable(value = "pid") Integer productId, Authentication authentication) throws ProductNotFoundException {
        String firebaseUId = SecurityUtil.getUidfromFirebase(authentication);
        return new CartDetailsDto(cartItemService.deleteCart(productId, firebaseUId));
    }

}
