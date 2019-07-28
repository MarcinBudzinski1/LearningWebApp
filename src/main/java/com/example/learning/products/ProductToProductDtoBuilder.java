package com.example.learning.products;

import com.example.learning.BaseEntity;
import com.example.learning.categories.Category;
import com.example.learning.categories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductToProductDtoBuilder {

    private final CompanyRepository companyRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository<Product> productRepository;

    @Autowired
    public ProductToProductDtoBuilder(CompanyRepository companyRepository, CategoryRepository categoryRepository, ProductRepository<Product> productRepository) {
        this.companyRepository = companyRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    ProductDto buildDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .productUrl(product.getProductUrl())
                .productPrice(product.getProductPrice())
                .companyId(Optional.ofNullable(product.getCompany()).map(BaseEntity::getId).orElse(null))
                .stockAmount(product.getStockAmount())
                .productType(product.getProductType())
                .categoryId(Optional.ofNullable(product.getCategory()).map(BaseEntity::getId).orElse(null))
                .categoryName(Optional.ofNullable(product.getCategory()).map(Category::getName).orElse(null))
                .build();
    }

    Product buildEntity(ProductDto dto) {
        Product product;
        product = productRepository.getOne(dto.getId());
        product.setProductName(dto.getProductName());
        product.setDescription(dto.getDescription());
        product.setProductUrl(dto.getProductUrl());
        product.setProductPrice(dto.getProductPrice());
        product.setCompany(Optional.ofNullable(dto.getCompanyId()).map(companyRepository::getOne).orElse(null));
        product.setStockAmount(dto.getStockAmount());
        product.setProductType(dto.getProductType());
        product.setCategory(Optional.ofNullable(dto.getCategoryId()).map(categoryRepository::getOne).orElse(null));
        return product;
    }
}
