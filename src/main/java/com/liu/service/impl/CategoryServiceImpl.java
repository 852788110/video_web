package com.liu.service.impl;

import com.liu.nosql.mongodb.document.Category;
import com.liu.nosql.mongodb.repository.CategoryRepository;
import com.liu.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category get(String categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()){
            return category.get();
        }
        return null;
    }

    @Override
    public List<Category> list() {
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }

    @Override
    public int add(Category category) {
        category.setId(null);
        categoryRepository.save(category);
        return 1;
    }

    @Override
    public int delete(String categoryId) {
        categoryRepository.deleteById(categoryId);
        return 0;
    }
}
