package com.jjoony.assetmanagement.domain.trasactions.repository;

import com.jjoony.assetmanagement.domain.trasactions.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public interface TransactionsRepository extends JpaRepository<Transactions, Long>, TransactionsRepositoryCustom {

}
