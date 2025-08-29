package com.rawatyash.xcommerce.service;

import com.rawatyash.xcommerce.model.Category;
import com.rawatyash.xcommerce.reposiltory.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImplementation implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> createCategory(Category category) {
        categoryRepository.save(category);
        return new ArrayList<>();
    }

    @Override
    public void deleteCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);

        if (category.isPresent()) {
            categoryRepository.delete(category.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found" + id);
        }

    }

    @Override
    public void updateCategory(Long id, Category category) {
        Optional<Category> savedCategoryOptional = categoryRepository.findById(id);

        if (savedCategoryOptional.isPresent()) {
            Category existingCategory = savedCategoryOptional.get();
            existingCategory.setName(category.getName());
            categoryRepository.save(existingCategory);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found" + id);
        }

    }
}
