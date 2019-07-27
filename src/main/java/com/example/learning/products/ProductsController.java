package com.example.learning.products;

import com.example.learning.products.datatables.DataTablesResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/products")
public class ProductsController {

    private final ProductService productService;

    @Autowired
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/list")
    public String productsList(@RequestParam(required = false) String text, Model model) {
        model.addAttribute("products", productService.findProductsForCustomer(text, null));
        model.addAttribute("query", StringUtils.defaultIfBlank(text, ""));
        model.addAttribute("cartActive", true);
        return "productsList";
    }

    @GetMapping("/table")
    public String productsTable(Model model) {
        model.addAttribute("firstTime", true);
        model.addAttribute("cartActive", true);
        return "productsTable";
    }


    @RequestMapping(value = "/tableData", method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResponse<ProductDto> find(@RequestParam(required = false) Integer start,
                                               @RequestParam(required = false) Integer length,
                                               @RequestParam(required = false) String sortColumn,
                                               @RequestParam(required = false) String sortOrder,
                                               @RequestParam(required = false) String searchText) {
        return null;
    }
}
