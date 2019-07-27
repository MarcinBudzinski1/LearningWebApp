package com.example.learning.categories;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryDto {
    private Long id;
    private Long parentId;
    private Integer depth;
    private String name;
    private CategoryDto parentCat;
    private CategoryState state;

    public String getText() {
        return id + ". " + name;
    }

    public String getParent() {
        return parentId == null ? "#" : parentId.toString();
    }

}
