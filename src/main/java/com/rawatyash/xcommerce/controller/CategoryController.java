package com.rawatyash.xcommerce.controller;

import com.rawatyash.xcommerce.model.Category;
import com.rawatyash.xcommerce.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/api")
public class CategoryController {

    private CategoryService categoryService;
    private List<Category> categories = new ArrayList<>();

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/public/categories")
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> allCategories = categoryService.getCategories();
        return new ResponseEntity<>(allCategories, HttpStatus.OK);
    }

    @PostMapping("/admin/category/add")
    public ResponseEntity<List<Category>> createCategory(@RequestBody Category category) {
        List<Category> createdCategory = categoryService.createCategory(category);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/category/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        try {
            categoryService.deleteCategory(id);
            return new ResponseEntity<>("OK", HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }
    @PutMapping("/admin/category/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Long id,@RequestBody Category category) {
        try {
            categoryService.updateCategory(id, category);
            return new ResponseEntity<>("OK", HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }
}
