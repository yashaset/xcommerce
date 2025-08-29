package com.rawatyash.xcommerce.response;

import com.rawatyash.xcommerce.request.CategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDTO {
    private List<CategoryDTO> content;
}
