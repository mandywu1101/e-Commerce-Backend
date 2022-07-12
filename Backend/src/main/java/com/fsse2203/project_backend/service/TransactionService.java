package com.fsse2203.project_backend.service;

import com.fsse2203.project_backend.data.Transaction.data.TransactionDetailedData;
import com.fsse2203.project_backend.exception.*;

public interface TransactionService {
    TransactionDetailedData createTransaction(String firebaseId) throws ProductNotFoundException, TransactionExistedException, TransactionNotFoundException, CartItemNotFoundException;
    TransactionDetailedData getAllTransaction(String firebaseUid, Integer transactionId) throws TransactionNotFoundException;
    TransactionDetailedData updateTransactionStatus(String firebaseUid, Integer transactionId) throws ProductNotFoundException, TransactionNotFoundException, StockNotEnoughException, TransactionErrorException;
    TransactionDetailedData finishCheckout(String firebaseUid, Integer transactionId) throws TransactionNotFoundException, TransactionErrorException;
}
