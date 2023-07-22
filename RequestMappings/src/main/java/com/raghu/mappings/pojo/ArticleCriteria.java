package com.raghu.mappings.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArticleCriteria {
    private String bodyLike;

    @SuppressWarnings("unused")
    public ArticleCriteria() {
    }
}
