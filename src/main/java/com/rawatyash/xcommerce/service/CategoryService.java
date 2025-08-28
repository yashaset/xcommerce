package com.rawatyash.xcommerce.service;

import com.rawatyash.xcommerce.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    List<Category> createCategory(Category category);

    List<Category> getCategories();

    void deleteCategory(Long id);
    void updateCategory(Long id, Category category);
}
