package com.example.learning.users;

import lombok.*;

import javax.persistence.Embeddable;

@Setter
@Getter
@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class UserAddress {

    private String street;
    private String building;
    private String flat;
    private String city;
    private String country;
    private String zipCode;

}
