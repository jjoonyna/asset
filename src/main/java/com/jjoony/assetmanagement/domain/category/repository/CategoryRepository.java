package com.jjoony.assetmanagement.domain.category.repository;

import com.jjoony.assetmanagement.domain.category.entity.Category;
import com.jjoony.assetmanagement.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryRepositoryCustom {
    Optional<Category> findByNameAndMember(String name, Member member);
}
