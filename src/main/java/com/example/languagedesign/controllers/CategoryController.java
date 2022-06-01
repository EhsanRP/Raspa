package com.example.languagedesign.controllers;

import com.example.languagedesign.commands.CategoryCommand;
import com.example.languagedesign.commands.RecipeCommand;
import com.example.languagedesign.services.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Value
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    CategoryService categoryService;

    @GetMapping("/all")
    public Set<CategoryCommand> getAllCategories() {
        return categoryService.findAllCategories();
    }

    @GetMapping("/{categoryId}")
    public CategoryCommand getCategoryById(@PathVariable String categoryId) {
        return categoryService.findCategoryById(Long.valueOf(categoryId));
    }

    @PostMapping("/save")
    public CategoryCommand saveCategory(@RequestBody CategoryCommand categoryCommand) {
        return categoryService.saveCategory(categoryCommand);
    }

    @DeleteMapping("/delete/{categoryId}")
    public void deleteCategory(@PathVariable String categoryId) {
        categoryService.deleteCategory(Long.valueOf(categoryId));
    }

    @PostMapping("/assign/category/{categoryId}/recipe/{recipeId}")
    public RecipeCommand assignRecipeToCategory(@PathVariable String categoryId, @PathVariable String recipeId) {
        return categoryService.assignRecipe(Long.valueOf(categoryId), Long.valueOf(recipeId));
    }

}
