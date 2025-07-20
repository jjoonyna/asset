package com.jjoony.assetmanagement.domain.trasactions.entity;

import com.jjoony.assetmanagement.domain.category.entity.Category;
import com.jjoony.assetmanagement.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class Transactions {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionsId;

    private LocalDate transactionDate;

    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Builder
    public Transactions(BigDecimal amount, LocalDate transactionDate, Category category, Member member) {
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.category = category;
        this.member = member;
    }
}
