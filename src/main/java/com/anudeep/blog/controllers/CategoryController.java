package com.anudeep.blog.controllers;

import com.anudeep.blog.payloads.ApiResponse;
import com.anudeep.blog.payloads.CategoryDto;
import com.anudeep.blog.payloads.UserDto;
import com.anudeep.blog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto createCategoryDto = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(createCategoryDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto , @PathVariable("id") Integer userId){
        CategoryDto createCategoryDto = this.categoryService.updateCategory(categoryDto,userId);
        return new ResponseEntity<>(createCategoryDto,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("id") Integer userId)
    {
        this.categoryService.deleteCategory(userId);

        return  new ResponseEntity(new ApiResponse("Category deleted Sucess",true),HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getcategories()
    {
        return ResponseEntity.ok(this.categoryService.getAllCategory());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("id") int id)
    {
        return ResponseEntity.ok(this.categoryService.getCategory(id));
    }
}
