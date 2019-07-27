package com.example.learning.carts;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CartService {

    public BigDecimal calculateTotalCartPrice(Cart cart){
        BigDecimal productsPrice =cart.getOrderLines()
                .stream()
                .map(e->e.getProductPrice()
                        .multiply(BigDecimal.valueOf(e.getQuantity()))).reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
        return productsPrice.add(calculateDeliveryCost(cart, productsPrice));
    }

    private BigDecimal calculateDeliveryCost(Cart cart, BigDecimal productsPrice) {
        // TODO: 26.07.2019
        return BigDecimal.valueOf(10);
    }
}

