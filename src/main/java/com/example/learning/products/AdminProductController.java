package com.example.learning.products;

import com.example.learning.categories.CategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

@Controller
@RequestMapping(value = "/admin")
public class AdminProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public AdminProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @PostMapping(value = "/product/add")
    public String addProduct(@RequestParam String productName,
                             @RequestParam String description,
                             @RequestParam String productUrl,
                             @RequestParam BigDecimal productPrice,
                             @RequestParam Long categoryId,
                             @RequestParam Integer stockAmount,
                             @RequestParam ProductType productType) {
        productService.createNewProduct(productName, description, productUrl, productPrice, categoryId, stockAmount, productType);
        return "redirect:/admin/products";
    }

    @GetMapping(value = "/product")
    public String addProduct(Model model) {
        model.addAttribute("productTypes", ProductType.values());
        model.addAttribute("categories", categoryService.filterCategories(null));
        return "addProduct";
    }

    @GetMapping(value = "/product/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        Optional<ProductDto> productToEdit = productService.findProductById(id);
        if (productToEdit.isPresent()) {
            model.addAttribute("productToEdit", productToEdit.get());
            model.addAttribute("productTypes", ProductType.values());
            model.addAttribute("categories", categoryService.filterCategories(null));
            return "editProduct";
        }
        return "redirect:/admin/product";
    }

    @PostMapping(value = "/product")
    public String editProduct(@ModelAttribute ProductDto product) {
        productService.updateProduct(product);
        return "redirect:/admin/products";
    }


    @GetMapping(value = "/products")
    public String showProducts(@RequestParam(required = false) String query, @RequestParam(required = false) String productType, Model model) {
        model.addAttribute("productsList", productService.findProductsToEdit(query, productType));
        model.addAttribute("productTypes", ProductType.values());
        model.addAttribute("query", StringUtils.defaultIfBlank(query, ""));
        model.addAttribute("productType", Arrays.stream(ProductType.values()).filter(e -> e.name().equals(productType)).findFirst().orElse(null));
        return "adminProductsList";
    }
}
