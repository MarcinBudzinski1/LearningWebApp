package com.example.learning.users;

import org.springframework.security.crypto.password.PasswordEncoder;

public class UserRegistrationDtoToUserBuilder {

    public static Customer rewriteToCustomer(CustomerRegistrationDto dto, PasswordEncoder passwordEncoder) {
        Customer customer = new Customer();
        customer.setFirstName(dto.getFirstName().trim());
        customer.setSurname(dto.getSurname().trim());
        customer.setUsername(dto.getUsername().trim());
        customer.setEMail(dto.getEmail().trim());
        customer.setPasswordHash(passwordEncoder.encode(dto.getPassword().trim()));

        customer.setUserAddress(UserAddress.builder()
                .city(dto.getCity().trim())
                .street(dto.getStreet().trim())
                .building(dto.getBuilding().trim())
                .flat(dto.getFlat().trim())
                .country(dto.getCountry().trim())
                .zipCode(dto.getZipCode().trim())
                .build());

        return customer;
    }
}
