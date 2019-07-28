package com.example.learning.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

}
