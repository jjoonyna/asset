package com.jjoony.assetmanagement.domain.trasactions.repository;

import com.jjoony.assetmanagement.domain.member.entity.Member;
import com.jjoony.assetmanagement.domain.trasactions.entity.Transactions;

import java.util.List;

public interface TransactionsRepositoryCustom {
    public List<Transactions> findByTransactionsOfMonth(int year, int month, Member member);
}
