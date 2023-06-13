package com.anudeep.blog.services;

import com.anudeep.blog.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {
     CategoryDto createCategory(CategoryDto categoryDto);
     CategoryDto updateCategory(CategoryDto categoryDto , Integer userId);
     void deleteCategory(Integer userId);
     CategoryDto getCategory(Integer userId);
     List<CategoryDto> getAllCategory();

}
