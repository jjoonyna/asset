package com.jjoony.assetmanagement.domain.trasactions.repository;

import com.jjoony.assetmanagement.domain.member.entity.Member;
import com.jjoony.assetmanagement.domain.trasactions.entity.QTransactions;
import com.jjoony.assetmanagement.domain.trasactions.entity.Transactions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class TransactionsRepositoryImpl implements TransactionsRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    QTransactions transactions = QTransactions.transactions;

    @Override
    public List<Transactions> findByTransactionsOfMonth(int year, int month, Member member){
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        return queryFactory
                .select(transactions)
                .from(transactions)
                .join(transactions.category).fetchJoin()
                .where(
                        transactions.member.eq(member),
                        transactions.transactionDate.between(startDate, endDate)
                )
                .fetch();
    }
}
