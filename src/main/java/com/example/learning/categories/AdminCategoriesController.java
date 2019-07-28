package com.example.learning.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminCategoriesController {

    private final CategoryService categoriesService;

    @Autowired
    public AdminCategoriesController(CategoryService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @GetMapping("/categories")
    public String cats(Model model, @RequestParam(required = false) String searchText) {
        List<CategoryDto> categoryDtoList = categoriesService.filterCategories(searchText);
        model.addAttribute("catsdata", categoryDtoList);
        return "categories";
    }

    @PostMapping("/categories/moveCat")
    @ResponseBody
    public void moveCat(@RequestParam() String newParentId,
                        @RequestParam() String movedId) {
        categoriesService.moveCategory(newParentId, movedId);
    }

    @GetMapping("/category")
    public String addCategoryForm() {
        return "addCategory";
    }

    @PostMapping("/category")
    public String addCategory(@RequestParam String catName,@RequestParam Long parentId) {
        categoriesService.addCategory(catName, parentId);
        return  "redirect:/admin/categories";
    }
}
