package com.anudeep.blog.services.impl;

import com.anudeep.blog.entites.Category;
import com.anudeep.blog.exceptions.ResourceNotFoundException;
import com.anudeep.blog.payloads.CategoryDto;
import com.anudeep.blog.repositories.CategoryRepo;
import com.anudeep.blog.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    private ModelMapper modelMapper;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.dtoToCategory(categoryDto);
        Category savedcategory = this.categoryRepo.save(category);
        return this.categoryToDto(savedcategory);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto , Integer userId) {
        Category category = this.categoryRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("Category","id",userId));

        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDes(categoryDto.getCategoryDes());

        Category updatedcategory = this.categoryRepo.save(category);
        CategoryDto categoryDto1 = this.categoryToDto(updatedcategory);
        return categoryDto1;
    }

    @Override
    public void deleteCategory(Integer userId) {
        Category category = this.categoryRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("Category","id",userId));
        this.categoryRepo.delete(category);
    }

    @Override
    public CategoryDto getCategory(Integer userId) {
        Category category = this.categoryRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("Category","id",userId));
        CategoryDto categoryDto= this.categoryToDto(category);
        return categoryDto;
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = this.categoryRepo.findAll();
        List<CategoryDto> categoryDto = categories.stream().map(Category -> this.categoryToDto(Category)).collect(Collectors.toList());
        return categoryDto;
    }


    private Category dtoToCategory(CategoryDto categoryDto)
    {
        Category category = new Category();
        category.setCategoryId(categoryDto.getCategoryId());
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDes(categoryDto.getCategoryDes());

        return category;

    }

    private CategoryDto categoryToDto(Category category)
    {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(category.getCategoryId());
        categoryDto.setCategoryTitle(category.getCategoryTitle());
        categoryDto.setCategoryDes(category.getCategoryDes());

        return categoryDto;

    }
}
