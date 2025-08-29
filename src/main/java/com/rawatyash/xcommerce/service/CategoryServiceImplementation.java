package com.rawatyash.xcommerce.service;

import com.rawatyash.xcommerce.mapper.CategoryMapper;
import com.rawatyash.xcommerce.model.Category;
import com.rawatyash.xcommerce.reposiltory.CategoryRepository;
import com.rawatyash.xcommerce.request.CategoryDTO;
import com.rawatyash.xcommerce.response.CategoryResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@Service
public class CategoryServiceImplementation implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryResponseDTO getCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOList = categories.stream().map(CategoryMapper::toDTO).toList();
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setContent(categoryDTOList);
        return categoryResponseDTO;
    }

    @Override
    public CategoryResponseDTO createCategory(CategoryDTO categoryDTO) {
        Category category = CategoryMapper.toEntity(categoryDTO);
        categoryRepository.save(category);
        return new CategoryResponseDTO();
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
    public void updateCategory(Long id, CategoryDTO category) {
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
