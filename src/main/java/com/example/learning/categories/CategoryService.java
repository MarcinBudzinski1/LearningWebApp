package com.example.learning.categories;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public abstract List<CategoryDto> filterCategories(String searchText);

    public List<CategoryDto> getCategories(){
        List<Category> categoryList = categoryRepository.getCategories();
        return categoryList.stream()
                .map(this::buildCategoryDto).collect(Collectors.toList());
    }

    public Optional<Category> getCategoryById(Long movedId) {
        return categoryRepository.findCategoryById(movedId);
    }

    public void moveCategory(String newParentId, String movedId) {
        Category movedCategory = getCategoryById(Long.valueOf(movedId)).get();
        movedCategory.setParentId(Long.valueOf(newParentId));
        categoryRepository.updateCategory(movedCategory);
    }


    protected CategoryDto buildCategoryDto(Category c) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(c.getId());
        categoryDto.setParentId(c.getParentId());
        categoryDto.setName(c.getName());
        categoryDto.setDepth(c.getDepth());
        categoryDto.setState(new CategoryState());
        return categoryDto;
    }

    protected CategoryDto populateStateAndOpenParents(CategoryDto dto, String searchText) {
        if (searchText != null && dto.getName().equals(searchText.trim())) {
            dto.getState().setOpened(true);
            dto.getState().setSelected(true);
            openParent(dto);
        }
        return dto;
    }

    protected void openParent(CategoryDto child) {
        CategoryDto parentCat = child.getParentCat();
        if (parentCat == null) {
            return;
        }
        parentCat.getState().setOpened(true);
        openParent(parentCat);
    }

    public void addCategory(String catName, Long parentId) {
        categoryRepository.save(new Category(parentId == 0 ? null : parentId, catName));
    }
}
