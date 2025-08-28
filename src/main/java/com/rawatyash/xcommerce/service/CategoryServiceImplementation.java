package com.rawatyash.xcommerce.service;

import com.rawatyash.xcommerce.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImplementation implements CategoryService {
    private List<Category> categories = new ArrayList<>();

    @Override
    public List<Category> getCategories() {
        return categories;
    }

    @Override
    public List<Category> createCategory(Category category) {
        categories.add(category);
        return categories;
    }

    @Override
    public void deleteCategory(Long id) {
        Category d = categories.stream().filter(c -> c.getId() == id
                ).findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found"));
        if (d == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found" + id);
        }
        categories.remove(d);
    }

    @Override
    public void updateCategory(Long id, Category category) {
        Optional<Category> optionalCategory = categories.stream()
                .filter(c -> c.getId()
                        .equals(id))
                .findFirst();
        if (optionalCategory.isPresent()) {
            Category existingCategory = optionalCategory.get();
            existingCategory.setName(category.getName());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found" + id);
        }

    }
}
