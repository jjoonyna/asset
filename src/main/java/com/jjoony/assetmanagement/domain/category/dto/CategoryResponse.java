package com.jjoony.assetmanagement.domain.category.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryResponse {
    private boolean type;
    private String name;
    private boolean isDefault;

}
