package com.example.languagedesign.controllers;

import com.example.languagedesign.commands.IngredientCommand;
import com.example.languagedesign.services.IngredientService;
import com.example.languagedesign.services.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Value
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/ingredients/{recipeId}")
public class IngredientController {

    RecipeService recipeService;
    IngredientService ingredientService;

    @GetMapping("/all")
    public Set<IngredientCommand> findAllRecipeIngredients(@PathVariable String recipeId) {
        return recipeService.findRecipeIngredients(Long.valueOf(recipeId));
    }

    @PostMapping("/save")
    public IngredientCommand saveIngredient(@PathVariable String recipeId, @RequestBody IngredientCommand ingredientCommand) {
        return ingredientService.saveIngredientCommand(Long.valueOf(recipeId), ingredientCommand);
    }

    @DeleteMapping("/delete/{ingredientId}")
    public void deleteIngredient(@PathVariable String ingredientId) {
        ingredientService.deleteById(Long.valueOf(ingredientId));
    }
}
