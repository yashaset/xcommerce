package com.rawatyash.xcommerce.mapper;

import com.rawatyash.xcommerce.model.Category;
import com.rawatyash.xcommerce.request.CategoryDTO;

public class CategoryMapper {
    public static CategoryDTO toDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }
    public static Category toEntity(CategoryDTO dto){
        Category category = new Category();
        category.setId(dto.getId());
        category.setName(dto.getName());
        return category;
    }
}
