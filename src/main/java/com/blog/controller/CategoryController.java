package com.blog.controller;

import com.blog.payloads.CategoryDto;
import com.blog.payloads.PostDto;
import com.blog.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping("/category")
    public ResponseEntity<CategoryDto> postCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto new_category = categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(new_category, HttpStatus.CREATED);
    }

    @PutMapping("/category/{category_id}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer category_id){
        CategoryDto updated_category = categoryService.updateCategory(categoryDto,category_id);
        return ResponseEntity.ok(updated_category);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        List<CategoryDto> allCategories = categoryService.getAllCategory();
        return new ResponseEntity<List<CategoryDto>>(allCategories,HttpStatus.OK);
    }

    @GetMapping("/category/{category_id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer category_id){
        CategoryDto categoryDto = categoryService.getCategory(category_id);
        return ResponseEntity.ok(categoryDto);
    }

    @DeleteMapping("/category/{category_id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer category_id){
        categoryService.deleteCategory(category_id);
        return ResponseEntity.ok(Map.of("message","Category deleted successfully"));
    }


}
