package com.example.learning.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository<T extends User> extends JpaRepository<T, Long> {

    Optional<T> findByUsername(String username);

    boolean existsByUsername(String username);

}
