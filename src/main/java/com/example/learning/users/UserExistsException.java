package com.example.learning.users;

public class UserExistsException extends RuntimeException {

    UserExistsException(String message) {
        super(message);
    }
}
