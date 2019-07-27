package com.example.learning.carts;

import com.example.learning.UserContextService;
import com.example.learning.products.Product;
import com.example.learning.products.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {

    private final ProductRepository<Product> productRepository;
    private final UserContextService userContextService;

    public CartController(ProductRepository<Product> productRepository, UserContextService userContextService) {
        this.productRepository = productRepository;
        this.userContextService = userContextService;
    }

    @PostMapping("/addToCart")
    public ResponseEntity<String> addToCart(@RequestParam(required = false) String prodId) {
        productRepository.findProductById(Long.valueOf(prodId)).ifPresent(userContextService::addProductToCart);
        return ResponseEntity.ok().body(userContextService.getCartAsJson());
    }

    @GetMapping(value = "/cartElements")
    public ResponseEntity<String> cartElements() {
        String cartAsJson = userContextService.getCartAsJson();
        if (cartAsJson == null) {
            return ResponseEntity.badRequest().body("Brak produktów");
        }
        return ResponseEntity.ok().body(cartAsJson);
    }
}
