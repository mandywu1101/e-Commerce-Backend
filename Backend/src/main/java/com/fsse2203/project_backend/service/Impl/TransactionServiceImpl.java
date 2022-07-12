package com.fsse2203.project_backend.service.Impl;

import com.fsse2203.project_backend.data.CartItem.entity.CartItemEntity;
import com.fsse2203.project_backend.data.Transaction.data.Status;
import com.fsse2203.project_backend.data.Transaction.data.TransactionDetailedData;
import com.fsse2203.project_backend.data.Transaction.data.TransactionProductDetailedData;
import com.fsse2203.project_backend.data.Transaction.entity.TransactionEntity;
import com.fsse2203.project_backend.data.Transaction.entity.TransactionProductEntity;
import com.fsse2203.project_backend.data.Transaction.data.TransactionProductSnapshot;
import com.fsse2203.project_backend.data.user.entity.UserEntity;
import com.fsse2203.project_backend.exception.*;
import com.fsse2203.project_backend.repository.TransactionProductRepository;
import com.fsse2203.project_backend.repository.TransactionRepository;
import com.fsse2203.project_backend.service.CartItemService;
import com.fsse2203.project_backend.service.ProductService;
import com.fsse2203.project_backend.service.TransactionService;
import com.fsse2203.project_backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final CartItemService cartItemService;
    private final UserService userService;
    private final TransactionRepository transactionRepository;
    private final TransactionProductRepository transactionProductRepository;
    private final ProductService productService;

    public TransactionServiceImpl(CartItemService cartItemService, UserService userService, TransactionRepository transactionRepository, TransactionProductRepository transactionProductRepository, ProductService productService) {
        this.cartItemService = cartItemService;
        this.userService = userService;
        this.transactionRepository = transactionRepository;
        this.transactionProductRepository = transactionProductRepository;
        this.productService = productService;
    }

    Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

    public TransactionDetailedData createTransaction(String firebaseId) throws ProductNotFoundException, TransactionExistedException, TransactionNotFoundException, CartItemNotFoundException {
        UserEntity userEntity = userService.getUserEntity(firebaseId);

        if (checkTransactionStatus(userEntity.getUserID())) {
            List<CartItemEntity> cartItemEntityList = cartItemService.getAllCartItemEntityByFirebaseUid(firebaseId);
            if (!cartItemEntityList.isEmpty()) {
                Status status = Status.PREPARED;
                BigDecimal total = BigDecimal.ZERO;
                LocalDateTime localDateTime = LocalDateTime.now();

                for (CartItemEntity cartItemEntity : cartItemEntityList) {
                    total = total.add(cartItemEntity.getProduct().getPrice().multiply(BigDecimal.valueOf(
                            cartItemEntity.getQuantity())));
                }
                TransactionEntity transactionEntity = new TransactionEntity(userEntity, localDateTime, status, total);
                transactionRepository.save(transactionEntity);

                List<TransactionProductDetailedData> transactionProductDetailedDataList = new ArrayList<>();
                for (CartItemEntity cartItemEntity : cartItemEntityList) {
                    TransactionProductEntity transactionProductEntity = new TransactionProductEntity(
                            cartItemEntity, transactionEntity);
                    transactionProductEntity = transactionProductRepository.save(transactionProductEntity);
                    System.out.println(transactionProductEntity);
                    TransactionProductSnapshot transactionProductSnapshot = new TransactionProductSnapshot(transactionProductEntity);
                    TransactionProductDetailedData transactionProductDetailedData = new TransactionProductDetailedData(transactionProductEntity, transactionProductSnapshot);
                    transactionProductDetailedDataList.add(transactionProductDetailedData);
                }
                transactionRepository.save(transactionEntity);
                return new TransactionDetailedData(
                        transactionEntity, transactionProductDetailedDataList);
            }
            throw new ProductNotFoundException();
        }
        throw new TransactionExistedException();
    }

    public TransactionDetailedData getAllTransaction(String firebaseUid, Integer transactionId) throws TransactionNotFoundException {
        UserEntity userEntity = userService.getUserEntity(firebaseUid);
        TransactionEntity searchTransactionEntity = transactionRepository.findAllByTransactionIdAndUserUserID(
                transactionId, userEntity.getUserID());

        if (searchTransactionEntity != null) {
            List<TransactionProductEntity> searchTransactionProductEntity = transactionProductRepository.findAllByTransactionEntityTransactionId(transactionId);
            List<TransactionProductDetailedData> transactionProductDetailedDataList = new ArrayList<>();
            for (TransactionProductEntity transactionProductEntity : searchTransactionProductEntity) {
                TransactionProductSnapshot transactionProductSnapshot = new TransactionProductSnapshot(transactionProductEntity);
                TransactionProductDetailedData transactionProductDetailedData = new TransactionProductDetailedData(
                        transactionProductEntity, transactionProductSnapshot);
                transactionProductDetailedDataList.add(transactionProductDetailedData);
            }
            TransactionDetailedData transactionDetailedData = new TransactionDetailedData(
                    searchTransactionEntity, transactionProductDetailedDataList);

            return transactionDetailedData;
        }
        throw new TransactionNotFoundException();
    }


    public TransactionDetailedData updateTransactionStatus(String firebaseUid, Integer transactionId) throws ProductNotFoundException, TransactionNotFoundException, StockNotEnoughException, TransactionErrorException {
        UserEntity userEntity = userService.getUserEntity(firebaseUid);
        TransactionEntity searchTransactionEntity = transactionRepository.findAllByTransactionIdAndUserUserID(
                transactionId, userEntity.getUserID());
        if (searchTransactionEntity != null) {
            if (searchTransactionEntity.getStatus() != Status.PREPARED) {
                throw new TransactionErrorException();
            }
            Status status = Status.PROCESSING;
            searchTransactionEntity.setStatus(status);

            List<TransactionProductEntity> searchTransactionProductEntity = transactionProductRepository.findAllByTransactionEntityTransactionId(transactionId);
            List<TransactionProductDetailedData> transactionProductDetailedDataList = new ArrayList<>();
            for (TransactionProductEntity transactionProductEntity : searchTransactionProductEntity) {
                TransactionProductSnapshot transactionProductSnapshot = new TransactionProductSnapshot(transactionProductEntity);
                TransactionProductDetailedData transactionProductDetailedData = new TransactionProductDetailedData(
                        transactionProductEntity, transactionProductSnapshot);
                transactionProductDetailedDataList.add(transactionProductDetailedData);

                Integer productId = transactionProductEntity.getProductId();
                Integer soldStock = transactionProductEntity.getQuantity();
                if (soldStock > transactionProductEntity.getStock()) {
                    throw new StockNotEnoughException();
                }
                productService.deduceStock(soldStock, productId);
            }
            transactionRepository.save(searchTransactionEntity);
            TransactionDetailedData transactionDetailedData = new TransactionDetailedData(
                    searchTransactionEntity, transactionProductDetailedDataList);


            return transactionDetailedData;
        }
        throw new TransactionNotFoundException();
    }

    public TransactionDetailedData finishCheckout(String firebaseUid, Integer transactionId) throws TransactionNotFoundException, TransactionErrorException {
        UserEntity userEntity = userService.getUserEntity(firebaseUid);
        TransactionEntity searchTransactionEntity = transactionRepository.findAllByTransactionIdAndUserUserID(
                transactionId, userEntity.getUserID());
        if (searchTransactionEntity != null) {
            if (searchTransactionEntity.getStatus() != Status.PROCESSING) {
                throw new TransactionErrorException();
            }
            Status status = Status.SUCCESS;
            searchTransactionEntity.setStatus(status);

            List<TransactionProductEntity> searchTransactionProductEntity = transactionProductRepository.findAllByTransactionEntityTransactionId(transactionId);
            List<TransactionProductDetailedData> transactionProductDetailedDataList = new ArrayList<>();
            for (TransactionProductEntity transactionProductEntity : searchTransactionProductEntity) {
                TransactionProductSnapshot transactionProductSnapshot = new TransactionProductSnapshot(transactionProductEntity);
                TransactionProductDetailedData transactionProductDetailedData = new TransactionProductDetailedData(
                        transactionProductEntity, transactionProductSnapshot);
                transactionProductDetailedDataList.add(transactionProductDetailedData);
                cartItemService.emptyCart(transactionProductEntity.getProductId(), userEntity.getUserID());
            }
            transactionRepository.save(searchTransactionEntity);
            TransactionDetailedData transactionDetailedData = new TransactionDetailedData(
                    searchTransactionEntity, transactionProductDetailedDataList);

            return transactionDetailedData;
        }
        logger.warn("TransactionNotFoundException is threw");
        throw new TransactionNotFoundException();
    }

    public boolean checkTransactionStatus(Integer userID) throws TransactionExistedException {
        List<TransactionEntity> searchedTransactionEntityList = transactionRepository.findAllByUserUserID(userID);
        for (TransactionEntity transactionEntity : searchedTransactionEntityList) {
            if (transactionEntity == null) {
                return true;
            }
//            else if (transactionEntity.getStatus() != Status.SUCCESS) {
//               throw new TransactionExistedException();
//            }

        }
        return true;
    }


}

