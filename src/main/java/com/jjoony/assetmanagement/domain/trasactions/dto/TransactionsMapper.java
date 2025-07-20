package com.jjoony.assetmanagement.domain.trasactions.dto;

import com.jjoony.assetmanagement.domain.category.entity.Category;
import com.jjoony.assetmanagement.domain.member.entity.Member;
import com.jjoony.assetmanagement.domain.trasactions.entity.Transactions;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TransactionsMapper {

    public Transactions toEntity(TransactionsRequest transactionsRequest, Member member, Category category) {
        return Transactions.builder()
                .amount(transactionsRequest.getAmount())
                .transactionDate(LocalDate.now())
                .category(category)
                .member(member)
                .build();
    }

    public TransactionsResponse toResponse(Transactions transactions, Member member){
        return TransactionsResponse.builder()
                .amount(transactions.getAmount())
                .transactionDate(transactions.getTransactionDate())
                .category(transactions.getCategory().getName())
                .email(member.getEmail())
                .build();
    }
}
