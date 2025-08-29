package com.rawatyash.xcommerce.service;
import com.rawatyash.xcommerce.request.CategoryDTO;
import com.rawatyash.xcommerce.response.CategoryResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
    CategoryResponseDTO createCategory(CategoryDTO category);

    CategoryResponseDTO getCategories(Integer pageNumber, Integer pageSize);

    void deleteCategory(Long id);
    void updateCategory(Long id, CategoryDTO category);
}
