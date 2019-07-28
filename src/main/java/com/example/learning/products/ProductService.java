package com.example.learning.products;

import com.example.learning.categories.CategoryService;
import com.example.learning.products.datatables.DataTablesOrder;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository<Product> productRepository;
    private final ProductToProductDtoBuilder productToProductDtoBuilder;
    private final CategoryService categoryService;

    public ProductService(ProductRepository<Product> productRepository,
                          ProductToProductDtoBuilder productToProductDtoBuilder,
                          CategoryService categoryService) {
        this.productRepository = productRepository;
        this.productToProductDtoBuilder = productToProductDtoBuilder;
        this.categoryService = categoryService;
    }

    void createNewProduct(String productName,
                          String description,
                          String productUrl,
                          BigDecimal productPrice,
                          Long categoryId,
                          Integer stockAmount,
                          ProductType productType) {
    Product product = new Product();
    product.setProductName(productName);
    product.setDescription(description);
    product.setProductUrl(productUrl);
    product.setProductPrice(productPrice);
    if (categoryId != null){
        product.setCategory(categoryService.getCategoryById(categoryId).orElse(null));
    }
    product.setStockAmount(stockAmount);
    product.setProductType(productType);
    productRepository.save(product);
    }

    Optional<ProductDto> findProductById(Long id) {
        return productRepository.findProductById(id).map(productToProductDtoBuilder::buildDto);
    }

    List<Product> findProductsToEdit(String query, String productType) {
        return findProducts(query, productType);
    }

    Optional<Product> findProducts(Long id) {
        return productRepository.findProductById(id);
    }

    private List<Product> findProducts(String query, String productType) {
        if (StringUtils.isBlank(query) && StringUtils.isBlank(productType)) {
            return productRepository.findAll();
        }
        if (StringUtils.isBlank(query)) {
            return productRepository.findProductsByProductType(ProductType.valueOf(productType));
        }
        if (StringUtils.isBlank(productType)) {
            return productRepository.findProductsByProductName(query);
        }
        return productRepository.findProductsByProductNameAndProductType(query, ProductType.valueOf(productType));
    }

    List<ProductDto> findProductsForCustomer(String query, String productType) {
        return findProducts(query, productType)
                .stream()
                .filter(e -> ObjectUtils.defaultIfNull(e.getStockAmount(), 0) > 0)
                .map(productToProductDtoBuilder::buildDto)
                .collect(Collectors.toList());
    }

    void updateProduct(ProductDto productDto) {
        Product s = productToProductDtoBuilder.buildEntity(productDto);
        productRepository.save(s);
    }
}
