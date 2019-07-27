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
import java.util.Random;

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
        mockProductCoffee("Classica", "Wyjątkowy Smak");
        mockProductCoffee("Pienaroma", "Cudowny Smak");
        mockProductCoffee("Classic", "Wyjątkowy Smak");
        mockProductCoffee("Gold", "Wyjątkowy Smak");
        mockProductCoffee("Qualita", "Wyjątkowo delikatna");
        mockProductCoffee("Mattina", "Mocna");
        mockProductCoffee("Rossa", "Delikatna");
        mockProductCoffee("Argento", "Klasyczna");
        mockProductCoffee("Mago", "Mocna");
        mockProductCoffee("Dopio", "Wyjątkowo smaczna");
        mockProductCoffee("Lucia", "Wyjątkowy Smak");
        mockProductCoffee("Kimbo", "Wyjątkowy Smak");
        mockProductCoffee("Barbera", "Wyśmienity Smak");
        mockProductCoffee("Espresso", "Smaczna");
    }

    private void mockProductCoffee(String productName, String description) {
        Product product = new Product();
        product.setProductName(productName);
        product.setDescription(description);
        product.setProductUrl("https://kawyherbaty.pl/userdata/gfx/7826b0d5538ca24b5de21973a0bf4a9e.jpg");
        product.setProductType(ProductType.COFFEE);
        Random random = new Random();
        product.setStockAmount(random.nextInt(100));
        product.setProductPrice(BigDecimal.valueOf(random.nextInt(40)+40));
        product.setCategory(categoryRepository.findCategoryById(1L).orElse(null));
        productRepository.save(product);
    }

    private void initializeCategories() {
        createCategory(1L, "Kawa Ziarnista");
        createCategory(2L, "Kawa Rozpuszczalna");
        createCategory(3L,"Kawa Mielona");
        createCategory(4L, "Maszyny");
        createCategory(5L, "Dodatki");
    }

    private void createCategory(Long parentId, String name) {
        Category category = new Category();
        category.setParentId(parentId);
        category.setName(name);
        categoryRepository.save(category);
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
