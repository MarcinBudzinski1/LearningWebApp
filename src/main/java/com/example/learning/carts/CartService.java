package com.example.learning.carts;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static java.math.BigDecimal.*;
import static java.math.BigDecimal.valueOf;

@Service
public class CartService {

    public BigDecimal calculateTotalCartPrice(Cart cart){
        BigDecimal productsPrice =cart.getOrderLines()
                .stream()
                .map(e->e.getProductPrice()
                        .multiply(valueOf(e.getQuantity()))).reduce(BigDecimal::add)
                .orElse(ZERO);
        return productsPrice.add(calculateDeliveryCost(productsPrice));
    }

    private BigDecimal calculateDeliveryCost(BigDecimal productsPrice) {
        if(productsPrice.compareTo(valueOf(100)) > 0){return BigDecimal.valueOf(0);}
        return valueOf(10);
    }
}