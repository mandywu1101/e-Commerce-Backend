package com.fsse2203.project_backend.api;

import com.fsse2203.project_backend.config.EnvConfig;
import com.fsse2203.project_backend.data.Transaction.data.TransactionDetailedData;
import com.fsse2203.project_backend.data.Transaction.dto.TransactionProductDto;
import com.fsse2203.project_backend.data.Transaction.dto.TransactionResponseDto;
import com.fsse2203.project_backend.exception.*;
import com.fsse2203.project_backend.service.TransactionService;
import com.fsse2203.project_backend.util.SecurityUtil;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {EnvConfig.devBaseUrl, EnvConfig.prodBaseUrl}, maxAge = 3600)
@RestController
public class TransactionApi {
    private final TransactionService transactionService;

    public TransactionApi(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transaction/prepare")
    public TransactionResponseDto createTransaction(Authentication authentication) throws ProductNotFoundException, TransactionExistedException, TransactionNotFoundException, CartItemNotFoundException {
        String firebaseUId = SecurityUtil.getUidfromFirebase(authentication);
        TransactionDetailedData transactionDetailedData = transactionService.createTransaction(firebaseUId);
        TransactionResponseDto transactionResponseDto = new TransactionResponseDto(
                transactionDetailedData);
        return transactionResponseDto;
    }


    @GetMapping("/transaction/{tid}")
    public TransactionResponseDto getTransaction(@PathVariable(value = "tid") Integer transactionId,
                                                 Authentication authentication) throws TransactionNotFoundException {
        String firebaseUid = SecurityUtil.getUidfromFirebase(authentication);
        TransactionDetailedData transactionDetailedData = transactionService.getAllTransaction(firebaseUid, transactionId);
        TransactionResponseDto transactionResponseDto = new TransactionResponseDto(
                transactionDetailedData);
        return transactionResponseDto;
    }

    @PatchMapping("/transaction/{tid}/pay")
    public TransactionResponseDto updateTransaction(@PathVariable(value = "tid") Integer transactionId, Authentication authentication) throws ProductNotFoundException, TransactionNotFoundException, StockNotEnoughException, TransactionErrorException {
        String firebaseUid = SecurityUtil.getUidfromFirebase(authentication);
        TransactionDetailedData transactionDetailedData = transactionService.updateTransactionStatus(firebaseUid,transactionId);
        TransactionResponseDto transactionResponseDto = new TransactionResponseDto(
                transactionDetailedData);
        return transactionResponseDto;
    }

    @PatchMapping("/transaction/{tid}/finish")
    public TransactionResponseDto finishCheckout(@PathVariable (value = "tid") Integer transactionId, Authentication authentication) throws TransactionNotFoundException, TransactionErrorException {
        String firebaseUid = SecurityUtil.getUidfromFirebase(authentication);
        TransactionDetailedData transactionDetailedData = transactionService.finishCheckout(firebaseUid, transactionId);
        TransactionResponseDto transactionResponseDto = new TransactionResponseDto(
                transactionDetailedData);
        return transactionResponseDto;
    }
}
