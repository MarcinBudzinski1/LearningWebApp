package com.example.learning.users;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class CustomerRegistrationDto {

    @Pattern(regexp = "^[\\p{Lu}][\\p{Ll}]{2,}$", message = "Wymagane przynajmniej 3 znaki(pierwsza litera duża, reszta małe).")
    private String firstName;
    @Pattern(regexp = "^[\\p{Lu}][\\p{Ll}]{2,}(-[\\p{Lu}][\\p{Ll}]{2,})?$", message = "Wymagane przynajmniej 3 znaki(pierwsza litera duża, można podać także nazwisko dwuczłonowe).")
    private String surname;
    @Pattern(regexp = "^[\\p{Lu}][\\p{Ll}]{2,}$", message = "Wymagane przynajmniej 3 znaki(pierwsza litera duża, reszta małe).")
    private String username;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*", message = "Hasło jest wymagane. Musi zawierać od 10 do 20 znaków, jedną duża, jedna małą literę i cyfrę.")
    private String password;
    @Pattern(regexp = "^[\\w.]+@[\\w]+\\.[\\w]+(\\.[a-z]{2,3})?$", message = "Zły format adresu email")
    private String email;
    @NotBlank(message = "Pole musi zostać wypełnione")
    private String street;
    @NotBlank(message = "Pole musi zostać wypełnione")
    private String building;
    @NotBlank(message = "Pole musi zostać wypełnione")
    private String flat;
    @NotBlank(message = "Pole musi zostać wypełnione")
    private String city;
    @NotBlank(message = "Pole musi zostać wypełnione")
    private String country;
    @NotBlank(message = "Pole musi zostać wypełnione")
    private String zipCode;
    private boolean preferEmails;

}
