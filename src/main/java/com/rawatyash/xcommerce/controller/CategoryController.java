package com.rawatyash.xcommerce.controller;

import com.rawatyash.xcommerce.request.CategoryDTO;
import com.rawatyash.xcommerce.response.CategoryResponseDTO;
import com.rawatyash.xcommerce.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController()
@RequestMapping("/api")
public class CategoryController {

    private final CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/public/categories")
    public ResponseEntity<CategoryResponseDTO> getCategories() {
        CategoryResponseDTO allCategories = categoryService.getCategories();
        return new ResponseEntity<>(allCategories, HttpStatus.OK);
    }

    @PostMapping("/admin/category/add")
    public ResponseEntity<CategoryResponseDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryResponseDTO categoryResponseDTO = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(categoryResponseDTO, HttpStatus.CREATED);
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
    public ResponseEntity<String> updateCategory(@PathVariable Long id,@RequestBody CategoryDTO category) {
        try {
            categoryService.updateCategory(id, category);
            return new ResponseEntity<>("OK", HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }
}
