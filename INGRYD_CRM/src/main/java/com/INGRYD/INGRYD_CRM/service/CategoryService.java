package com.INGRYD.INGRYD_CRM.service;
import com.INGRYD.INGRYD_CRM.model.Category;
import com.INGRYD.INGRYD_CRM.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

    @Service
    @Transactional
    public class CategoryService {
        private final CategoryRepository categoryRepository;

        public CategoryService(CategoryRepository categoryRepository) {
            this.categoryRepository = categoryRepository;
        }

        public ResponseEntity<List<Category>> getAllCategories() {
            return new ResponseEntity<>(categoryRepository.findAll(), HttpStatus.OK);
        }
        public ResponseEntity<Category> getCategoryById(long id) {
            return new ResponseEntity<>(categoryRepository.findById(id).orElseThrow(), HttpStatus.OK);
        }
        public ResponseEntity<Category> getCategoryByName(String name) {
            return new ResponseEntity<>(categoryRepository.findByName(name), HttpStatus.OK);
        }
        public ResponseEntity<Category> createCategory(Category category) {
            category.setName(category.getName());
            return new ResponseEntity<>(categoryRepository.save(category), HttpStatus.CREATED);
        }
        public ResponseEntity<Category> updateCategory(String name, Category updatedCategory) {
            Category existingCategory = categoryRepository.findByName(name);
            existingCategory.setName(updatedCategory.getName());
            return new ResponseEntity<>(categoryRepository.save(existingCategory), HttpStatus.ACCEPTED);
        }
        public ResponseEntity<Category> deleteCategory(long id) {
            Category deletedCategory = categoryRepository.findById(id).orElseThrow();
            categoryRepository.deleteById(id);
            return new ResponseEntity<>(deletedCategory, HttpStatus.OK);
        }
    }

