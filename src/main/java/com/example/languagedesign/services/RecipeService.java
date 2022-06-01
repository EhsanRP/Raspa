package com.example.languagedesign.services;

import com.example.languagedesign.commands.IngredientCommand;
import com.example.languagedesign.commands.RecipeCommand;

import java.util.Set;

public interface RecipeService {

    Set<RecipeCommand> findAllRecipes();

    Set<RecipeCommand> findAllRecipesByCategoryId(Long categoryId);

    RecipeCommand findRecipeById(Long id);

    RecipeCommand saveRecipe(RecipeCommand recipeCommand);

    void deleteRecipe(Long id);

    Set<IngredientCommand> findRecipeIngredients(Long recipeId);

    RecipeCommand approveRecipe(Long recipeId);
}
