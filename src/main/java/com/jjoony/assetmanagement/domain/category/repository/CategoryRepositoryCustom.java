package com.jjoony.assetmanagement.domain.category.repository;

import com.jjoony.assetmanagement.domain.category.entity.Category;
import com.jjoony.assetmanagement.domain.member.entity.Member;

import java.util.List;

public interface CategoryRepositoryCustom {
    public List<Category> findByMemberCategories(Member member);
}
