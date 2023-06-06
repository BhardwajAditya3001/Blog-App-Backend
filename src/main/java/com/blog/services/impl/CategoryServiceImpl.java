package com.blog.services.impl;

import com.blog.entities.Category;
import com.blog.entities.User;
import com.blog.exceptions.ResourceNotException;
import com.blog.payloads.CategoryDto;
import com.blog.repositories.CategoryRepo;
import com.blog.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;

    public Category dtoToCategory(CategoryDto categoryDto){
        Category category = modelMapper.map(categoryDto, Category.class);
        return category;
    }

    public CategoryDto categoryToDto(Category category){
        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
        return categoryDto;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category new_category = dtoToCategory(categoryDto);
        categoryRepo.save(new_category);
        return categoryDto;
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) {
        Category category = categoryRepo.findById(id).orElseThrow(()->new ResourceNotException("Category","Id",id));
        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());
        Category updated_category = categoryRepo.save(category);
        return categoryToDto(updated_category);
    }

    @Override
    public CategoryDto getCategory(Integer id) {
        Category category = categoryRepo.findById(id).orElseThrow(()->new ResourceNotException("Category","Id",id));
        return categoryToDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = categoryRepo.findAll();
        List<CategoryDto> categoryDtos = categories.stream().map(category -> categoryToDto(category)).toList();
        return categoryDtos;
    }


    @Override
    public void deleteCategory(Integer id) {
        categoryRepo.deleteById(id);
    }
}
