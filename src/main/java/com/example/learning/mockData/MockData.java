package com.example.learning.mockData;

import com.example.learning.categories.Category;
import com.example.learning.categories.CategoryRepository;
import com.example.learning.products.*;
import com.example.learning.users.*;
import com.google.common.collect.Sets;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Service
public class MockData {

    private final UserRepository<Customer> userRepository;
    private final RoleRepository roleRepository;
    private final CategoryRepository categoryRepository;
    private final PasswordEncoder passwordEncoder;
    private final CompanyRepository companyRepository;
    private final ProductRepository<Product> productRepository;

    public MockData(UserRepository<Customer> userRepository, RoleRepository roleRepository, CategoryRepository categoryRepository, PasswordEncoder passwordEncoder, CompanyRepository companyRepository, ProductRepository<Product> productRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.categoryRepository = categoryRepository;
        this.passwordEncoder = passwordEncoder;
        this.companyRepository = companyRepository;
        this.productRepository = productRepository;
    }

    @PostConstruct
    public void mockData(){
        if (categoryRepository.checkSize() == 0){
            initializeCategories();
        }
        if (companyRepository.checkSize() == 0){
            initializeCompanies();
        }
        if (productRepository.checkSize() == 0) {
            initializeProducts();
        }
    }

    private void initializeProducts() {
        mockProduct("Classica", "Wyjątkowy Smak", BigDecimal.valueOf(95) ,50);
        mockProduct("Pienaroma", "Cudowny Smak", BigDecimal.valueOf(105) ,60);
        mockProduct("Classic", "Wyjątkowy Smak", BigDecimal.valueOf(99) ,50);
        mockProduct("Gold", "Wyjątkowy Smak", BigDecimal.valueOf(90) ,60);
        mockProduct("Qualita", "Wyjątkowo delikatna", BigDecimal.valueOf(79) ,50);
        mockProduct("Mattina", "Mocna", BigDecimal.valueOf(95) ,40);
        mockProduct("Rossa", "Delikatna", BigDecimal.valueOf(80) ,50);
        mockProduct("Argento", "Klasyczna", BigDecimal.valueOf(99) ,40);
        mockProduct("Mago", "Mocna", BigDecimal.valueOf(123) ,50);
        mockProduct("Dopio", "Wyjątkowo smaczna", BigDecimal.valueOf(111) ,50);
        mockProduct("Lucia", "Wyjątkowy Smak", BigDecimal.valueOf(110) ,40);
        mockProduct("Kimbo", "Wyjątkowy Smak", BigDecimal.valueOf(98) ,50);
        mockProduct("Barbera", "Wyśmienity Smak", BigDecimal.valueOf(94) ,60);
        mockProduct("Espresso", "Smaczna", BigDecimal.valueOf(85) ,50);

    }

    private void mockProduct(String productName,
                             String description,
                             BigDecimal productPrice,
                             Integer stockAmount) {
        Product product = new Product();
        product.setProductName(productName);
        product.setDescription(description);
        product.setProductUrl("https://kawyherbaty.pl/userdata/gfx/7826b0d5538ca24b5de21973a0bf4a9e.jpg");
        product.setProductPrice(productPrice);
        product.setProductType(ProductType.COFFEE);
        product.setStockAmount(stockAmount);
        productRepository.save(product);
    }

    private void initializeCategories() {
        
    }

    private void initializeCompanies() {
        mockCompany("Lavazza", Countries.ITALY);
        mockCompany("Danesi", Countries.ITALY);
        mockCompany("Novell", Countries.SPAIN);
        mockCompany("Candelas", Countries.SPAIN);
        mockCompany("Bogani", Countries.PORTUGAL);
        mockCompany("Rossio", Countries.PORTUGAL);
    }

    private void mockCompany(String companyName, Countries companyCountry){
        Company company = new Company();
        company.setCompanyCountry(companyCountry);
        company.setCompanyName(companyName);
        companyRepository.save(company);
    }

    @PostConstruct
    public void mockUsers(){
        Customer customer = new Customer();
        Role role = roleRepository.findRoleByRoleName(RoleTypeEnum.USER.getRoleName());
        customer.setUsername("User");
        customer.setPasswordHash(passwordEncoder.encode("user12345"));
        customer.setRoles(Sets.newHashSet(role));
        customer.setFirstName("Marcin");
        customer.setSurname("Budziński");
        customer.setUserAddress(UserAddress.builder().zipCode("01-000").street("Długa").city("Warszawa").building("11").flat("2").country("PL").build());
        userRepository.save(customer);
    }
}
