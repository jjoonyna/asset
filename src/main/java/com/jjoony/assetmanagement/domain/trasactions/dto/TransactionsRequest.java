package com.jjoony.assetmanagement.domain.trasactions.dto;



import com.jjoony.assetmanagement.domain.category.entity.Category;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class TransactionsRequest {

    private BigDecimal amount;
    private String category;

}
