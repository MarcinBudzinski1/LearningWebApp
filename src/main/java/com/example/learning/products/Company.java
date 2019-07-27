package com.example.learning.products;

import com.example.learning.BaseEntity;
import com.example.learning.users.Countries;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Company extends BaseEntity {

    private String companyName;
    private Countries companyCountry;

    public Company(String companyName, Countries companyCountry) {
        this.companyName = companyName;
        this.companyCountry = companyCountry;
    }
}
