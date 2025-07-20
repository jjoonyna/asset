package com.jjoony.assetmanagement.domain.trasactions.controller;

import com.jjoony.assetmanagement.domain.trasactions.dto.TransactionsRequest;
import com.jjoony.assetmanagement.domain.trasactions.dto.TransactionsResponse;
import com.jjoony.assetmanagement.domain.trasactions.service.TransactionsService;
import com.jjoony.assetmanagement.global.auth.PrincipalDetails;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionsController {

    private final TransactionsService transactionsService;

    @Operation(summary = "수입, 지출 등록", description = "사용자가 직접 수입, 지출을 등록하는  api 입니다.")
    @PostMapping
    public ResponseEntity<TransactionsResponse> createTransactions(@RequestBody TransactionsRequest transactionsRequest,
                                                                   @AuthenticationPrincipal PrincipalDetails principalDetails) {
        TransactionsResponse response = transactionsService.createTransactions(transactionsRequest, principalDetails);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "한달 수입,지출 조회", description = "사용자의 한달간 수입, 지출 내역을 조회하는 api 입니다.")
    @GetMapping
    public ResponseEntity<List<TransactionsResponse>> getMonthTransactions(@RequestParam int year, @RequestParam int month,
                                                                           @AuthenticationPrincipal PrincipalDetails principalDetails) {
        List<TransactionsResponse> response = transactionsService.getMonthTransactions(year, month, principalDetails);

        return ResponseEntity.ok(response);
    }
}
