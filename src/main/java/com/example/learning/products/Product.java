package com.example.learning.products;

import com.example.learning.BaseEntity;
import com.example.learning.categories.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "system_type", discriminatorType = DiscriminatorType.STRING)
public class Product extends BaseEntity {

    private String productName;
    private String description;
    private String productUrl;
    private BigDecimal productPrice;
    @ManyToOne
    private Company company;
    private Integer stockAmount;
    @Enumerated
    private ProductType productType;
    @ManyToOne
    private Category category;
}
