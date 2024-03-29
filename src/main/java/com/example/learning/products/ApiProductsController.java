package com.example.learning.products;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/api")
public class ApiProductsController {

    private final ProductService productService;

    public ApiProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/products")
    @ResponseBody
    public ResponseEntity<List<ProductDto>> showProducts(@RequestParam(required = false) String query, @RequestParam(required = false) String productType) {
        List<ProductDto> productsForCustomer = productService.findProductsForCustomer(query, productType);
        return ResponseEntity.ok().body(productsForCustomer);
    }

    @GetMapping(value = "/products/{id}")
    @ResponseBody
    public ResponseEntity<Product> showProducts(@PathVariable Long id) {
        return ResponseEntity.ok().body(productService.findProducts(id).orElse(null));
    }
}