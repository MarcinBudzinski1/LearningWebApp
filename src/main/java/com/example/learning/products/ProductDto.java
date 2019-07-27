package com.example.learning.products;

import lombok.*;

import java.math.BigDecimal;
import java.util.Optional;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;
    private String productName;
    private String description;
    private String productUrl;
    private BigDecimal productPrice;
    private Long companyId;
    private Integer stockAmount;
    private ProductType productType;
    private Long categoryId;
    private String categoryName;

    public String getProductTypePl() {
        return Optional.ofNullable(productType).map(ProductType::getType).orElse("");
    }

}
