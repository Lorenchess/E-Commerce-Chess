package com.chess4math.product.mappers;

import com.chess4math.product.dtos.CategoryDTO;
import com.chess4math.product.entities.Category;

public interface CategoryMapper {

    CategoryDTO entityToDTO(Category category);

    Category dtoToEntity(CategoryDTO categoryDTO);
}
