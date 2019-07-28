package com.example.learning.orders;

import com.example.learning.BaseEntity;
import com.example.learning.products.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter
@Entity
@ToString
@Table(name = "order_lines")
public class OrderLine extends BaseEntity implements Serializable {

    @OneToOne
    private Product product;

    private Integer quantity;

    @Column(name = "product_price")
    private BigDecimal productPrice;
}
