package com.example.learning.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Primary
public class SelectCategoriesService extends CategoryService {

    @Autowired
    public SelectCategoriesService(CategoryRepository categoryRepository) {
        super(categoryRepository);
    }

    public List<CategoryDto> filterCategories(String searchText) {
        Map<Long, CategoryDto> dtoMap = getCategories().stream()
                .collect(Collectors.toMap(CategoryDto::getId, v -> v));

        return dtoMap.values().stream()
                .peek(dto -> dto.setParentCat(dtoMap.get(dto.getParentId())))
                .map(dto -> populateStateAndOpenParents(dto, searchText))
                .collect(Collectors.toList());
    }
}
