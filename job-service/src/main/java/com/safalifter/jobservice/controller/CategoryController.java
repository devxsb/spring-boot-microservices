package com.safalifter.jobservice.controller;

import com.safalifter.jobservice.dto.CategoryDto;
import com.safalifter.jobservice.dto.JobDto;
import com.safalifter.jobservice.request.category.CategoryCreateRequest;
import com.safalifter.jobservice.request.category.CategoryUpdateRequest;
import com.safalifter.jobservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @PostMapping("/create")
    ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(modelMapper.map(categoryService.createCategory(request), CategoryDto.class));
    }

    @GetMapping("/getAll")
    ResponseEntity<List<CategoryDto>> getAll() {
        return ResponseEntity.ok(categoryService.getAll().stream()
                .map(category -> modelMapper.map(category, CategoryDto.class)).toList());
    }

    @GetMapping("/getCategoryById/{id}")
    ResponseEntity<CategoryDto> getCategoryById(@PathVariable String id) {
        return ResponseEntity.ok(modelMapper.map(categoryService.getCategoryById(id), CategoryDto.class));
    }

    @PutMapping("/update")
    ResponseEntity<JobDto> updateCategoryById(@RequestBody CategoryUpdateRequest request) {
        return ResponseEntity.ok(modelMapper.map(categoryService.updateCategoryById(request), JobDto.class));
    }

    @DeleteMapping("/deleteCategoryById/{id}")
    ResponseEntity<Void> deleteCategoryById(@PathVariable String id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.ok().build();
    }
}
