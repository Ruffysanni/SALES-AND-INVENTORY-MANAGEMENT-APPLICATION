package com.INGRYD.INGRYD_CRM.Controller;

import com.INGRYD.INGRYD_CRM.Service.CategoryService;
import com.INGRYD.INGRYD_CRM.model.Category;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@RequestMapping("/api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/all_categories")
    public ResponseEntity<List<Category>> searchAllCategories() {
        return categoryService.getAllCategories();
    }
    @GetMapping("/category_by_id/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable long id) {
        return categoryService.getCategoryById(id);
    }
    @GetMapping("/category_by_name")
    public ResponseEntity<Category> getCategoryByName(@RequestParam String name) {
        return categoryService.getCategoryByName(name);
    }
    @PostMapping("/new_category")
    public ResponseEntity<Category> postCategory(@Valid @RequestBody Category category) {
        return categoryService.createCategory(category);
    }
    @PutMapping("/update_category/{name}")
    public ResponseEntity<Category> updateCategory(@Valid @PathVariable String name,  @RequestBody Category updatedCategory) {
        return categoryService.updateCategory(name, updatedCategory);
    }
    @DeleteMapping("/delete_category/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable long id) {
        return categoryService.deleteCategory(id);
    }
}
