package com.example.languagedesign.services;

import com.example.languagedesign.commands.IngredientCommand;

import java.util.Set;

public interface IngredientService {

    Set<IngredientCommand> findAll();

    IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);

    void deleteById(Long ingredientId);

    Set<IngredientCommand> findByRecipeId(Long recipeId);

}
