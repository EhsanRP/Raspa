package com.example.languagedesign.controllers;

import com.example.languagedesign.commands.RecipeCommand;
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
@RequestMapping("/api/v1/recipes")
public class RecipeController {

    RecipeService recipeService;

    @GetMapping("/all")
    public Set<RecipeCommand> findAllApprovedRecipes() {
        log.debug("Getting all ingredients");
        return recipeService.findAllRecipesByIsApproved(true);
    }

    @GetMapping("/get/{recipeId}")
    public RecipeCommand findRecipeById(@PathVariable String recipeId) {
        log.debug("Getting recipe with id {}", recipeId);
        var recipe = recipeService.findRecipeById(Long.valueOf(recipeId));
        log.debug(recipe.toString());
        return recipe;
    }

    @GetMapping("/unapproved/all")
    public Set<RecipeCommand> findAllUnApprovedRecipes() {
        log.debug("Getting all unapproved recipes");
        return recipeService.findAllRecipesByIsApproved(false);
    }

    @GetMapping("/category/{categoryId}")
    public Set<RecipeCommand> findCategoryRecipes(@PathVariable String categoryId) {
        log.debug("Finding recipes with catergory name {}", categoryId);
        return recipeService.findAllRecipesByCategoryId(Long.valueOf(categoryId));
    }

    @PostMapping("/new")
    public RecipeCommand saveRecipe(@RequestBody RecipeCommand recipeCommand) {
        log.debug("Saving a new recipe with details below");
        log.debug(recipeCommand.toString());
        return recipeService.saveRecipe(recipeCommand);
    }

    @PatchMapping("/approve/{recipeId}")
    public RecipeCommand approveRecipe(@PathVariable String recipeId) {
        return recipeService.approveRecipe(Long.valueOf(recipeId));
    }

    @DeleteMapping("/delete/{recipeId}")
    public void deleteRecipe(@PathVariable String recipeId) {
        recipeService.deleteRecipe(Long.valueOf(recipeId));
    }

}
