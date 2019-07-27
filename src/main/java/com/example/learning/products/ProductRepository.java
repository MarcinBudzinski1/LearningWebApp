package com.example.learning.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;


public interface ProductRepository<T extends Product> extends JpaRepository<Product, Long>{

    Optional<T> findProductById(Long id);

    List<T> findProductsByProductType(ProductType productType);

    List<T> findProductsByProductName(String searchText);

    List<T> findProductsByProductNameAndProductType(String searchText, ProductType productType);


    @Query(value = "select count(c) from Category c")
    Long checkSize();
}
