package com.jjoony.assetmanagement.domain.category.entity;

import com.jjoony.assetmanagement.domain.member.entity.Member;
import com.jjoony.assetmanagement.domain.trasactions.entity.Transactions;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Setter
    private boolean type;

    @Setter
    @Column(nullable = false)
    private String name;

    private boolean isDefault;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @OneToMany
    private List<Transactions> transactions = new ArrayList<>();
    @Builder
    public Category(boolean type, String name, boolean isDefault, Member member) {
        this.type = type;
        this.name = name;
        this.isDefault = isDefault;
        this.member = member;
    }
}
