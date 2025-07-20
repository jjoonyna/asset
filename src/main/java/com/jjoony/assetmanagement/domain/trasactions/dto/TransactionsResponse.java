package com.jjoony.assetmanagement.domain.trasactions.dto;

import com.jjoony.assetmanagement.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Getter
public class TransactionsResponse {
    private LocalDate transactionDate;

    private BigDecimal amount;

    private String category;

    private String email;
}
