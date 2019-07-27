package com.example.learning.products;

import lombok.Getter;

@Getter
public enum ProductType {

    COFFEE("kawa"),
    DEVICES("urządzenia"),
    additives("dodatki");

    private String type;

    ProductType(String type) {
        this.type = type;
    }
}
