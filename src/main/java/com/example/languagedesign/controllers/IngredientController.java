package com.example.languagedesign.controllers;

import com.example.languagedesign.commands.IngredientCommand;
import com.example.languagedesign.domain.Ingredient;
import com.example.languagedesign.services.IngredientService;
import com.example.languagedesign.services.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Slf4j
@Value
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/ingredients")
public class IngredientController {

    RecipeService recipeService;
    IngredientService ingredientService;

    @GetMapping("/{recipeId}/all")
    public Set<IngredientCommand> findAllRecipeIngredients(@PathVariable String recipeId) {
        log.debug("Getting all ingredients for recipe with id {}",recipeId);
        return recipeService.findRecipeIngredients(Long.valueOf(recipeId));
    }

    @GetMapping("/all")
    public Set<IngredientCommand> findALl(){
        log.debug("Getting all ingredients");
        return ingredientService.findAll();
    }

    @PostMapping("/save")
    public IngredientCommand saveIngredient( @RequestBody IngredientCommand ingredientCommand) {
        log.debug("Saving ingredient with description {} amount {} and unit of measure {}",ingredientCommand.getDescription(),ingredientCommand.getAmount(),ingredientCommand.getUnitOfMeasure());
        return ingredientService.saveIngredientCommand(ingredientCommand);
    }

    @DeleteMapping("/delete/{ingredientId}")
    public void deleteIngredient(@PathVariable String ingredientId) {
        log.debug("Deleting ingredient with id {}", ingredientId);
        ingredientService.deleteById(Long.valueOf(ingredientId));
    }
}
