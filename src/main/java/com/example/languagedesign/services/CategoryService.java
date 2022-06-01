package com.example.languagedesign.services;

import com.example.languagedesign.commands.CategoryCommand;
import com.example.languagedesign.commands.RecipeCommand;

import java.util.Set;

public interface CategoryService {

    Set<CategoryCommand> findAllCategories();

    CategoryCommand findCategoryById(Long id);

    CategoryCommand saveCategory(CategoryCommand categoryCommand);

    RecipeCommand assignRecipe(Long categoryId, Long recipeId);

    void deleteCategory(Long id);
}
