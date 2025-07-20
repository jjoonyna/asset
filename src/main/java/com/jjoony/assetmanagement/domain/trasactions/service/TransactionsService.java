package com.jjoony.assetmanagement.domain.trasactions.service;

import com.jjoony.assetmanagement.domain.category.entity.Category;
import com.jjoony.assetmanagement.domain.category.repository.CategoryRepository;
import com.jjoony.assetmanagement.domain.member.entity.Member;
import com.jjoony.assetmanagement.domain.member.repository.MemberRepository;
import com.jjoony.assetmanagement.domain.trasactions.dto.TransactionsMapper;
import com.jjoony.assetmanagement.domain.trasactions.dto.TransactionsRequest;
import com.jjoony.assetmanagement.domain.trasactions.dto.TransactionsResponse;
import com.jjoony.assetmanagement.domain.trasactions.entity.Transactions;
import com.jjoony.assetmanagement.domain.trasactions.repository.TransactionsRepository;
import com.jjoony.assetmanagement.global.auth.PrincipalDetails;
import com.jjoony.assetmanagement.global.exception.ApiException;
import com.jjoony.assetmanagement.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionsService {

    private final MemberRepository memberRepository;
    private final TransactionsMapper transactionsMapper;
    private final TransactionsRepository transactionsRepository;
    private final CategoryRepository categoryRepository;

    public TransactionsResponse createTransactions(TransactionsRequest transactionsRequest, PrincipalDetails principalDetails) {

        log.info("사용자 수입,지출 등록: request={}", transactionsRequest);

        Member member = memberRepository.findByEmail(principalDetails.getMember().getEmail())
                .orElseThrow(()-> new ApiException(ErrorCode.MEMBER_NOT_FOUND));

        if (transactionsRequest.getCategory() == null){
            throw new ApiException(ErrorCode.CATEGORY_NOT_FOUND);
        }
        Category category = categoryRepository.findByNameAndMember(transactionsRequest.getCategory(), member)
                .orElseThrow(()-> new ApiException(ErrorCode.CATEGORY_NOT_FOUND));

        Transactions transactions = transactionsMapper.toEntity(transactionsRequest, member, category);

        transactionsRepository.save(transactions);

        log.info("수입, 지출 등록 완료: transactions={}", transactions);

        return transactionsMapper.toResponse(transactions, member);
    }

    public List<TransactionsResponse> getMonthTransactions(int year, int month, PrincipalDetails principalDetails) {

        log.info("사용자 한달 수입, 지출 조회: year={}, month={}, principalDetails={}", year, month, principalDetails);

        Member member = memberRepository.findByEmail(principalDetails.getMember().getEmail())
                .orElseThrow(()-> new ApiException(ErrorCode.MEMBER_NOT_FOUND));

        List<Transactions> transactionList = transactionsRepository.findByTransactionsOfMonth(year, month, member);

        return transactionList.stream()
                .map(transactions -> {
                    return transactionsMapper.toResponse(transactions, member);
                }).collect(Collectors.toList());
    }
}
