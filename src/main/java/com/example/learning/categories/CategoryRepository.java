package com.example.learning.categories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByNameLike(String text);

    @Query(value = "select count(c) from Category c")
    Long checkSize();

    default List<Category> getCategories() {
        return findAll();
    }

    default Optional<Category> findCategoryById(Long id) {
        return findById(id);
    }

    @Transactional
    default void updateCategory(Category categoryInMemoryDto) {
        save(categoryInMemoryDto);
    }
}
