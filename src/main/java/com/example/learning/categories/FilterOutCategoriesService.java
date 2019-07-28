package com.example.learning.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FilterOutCategoriesService extends CategoryService {

    @Autowired
    public FilterOutCategoriesService(CategoryRepository categoryRepository) {
        super(categoryRepository);
    }

    public List<CategoryDto> filterCategories(String searchText) {
        Map<Long, CategoryDto> dtoMap = getCategories().stream()
                .collect(Collectors.toMap(CategoryDto::getId, v -> v));
        return dtoMap.values().stream()
                .peek(dto -> dto.setParentCat(dtoMap.get(dto.getParentId())))
                .map(dto -> populateStateAndOpenParents(dto, searchText))
                .filter(e -> e.getState().isOpened())
                .collect(Collectors.toList());
    }
}
