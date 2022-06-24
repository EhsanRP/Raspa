package com.example.languagedesign.controllers;

import com.example.languagedesign.commands.RecipeCommand;
import com.example.languagedesign.services.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Value
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/recipes")
public class RecipeController {

    RecipeService recipeService;

    @GetMapping("/all")
    public Set<RecipeCommand> findAllApprovedRecipes() {
        return recipeService.findAllRecipesByIsApproved(true);
    }

    @GetMapping("/unapproved/all")
    public Set<RecipeCommand> findAllUnApprovedRecipes() {
        return recipeService.findAllRecipesByIsApproved(false);
    }

    @GetMapping("/category/{categoryId}")
    public Set<RecipeCommand> findCategoryRecipes(@PathVariable String categoryId) {
        return recipeService.findAllRecipesByCategoryId(Long.valueOf(categoryId));
    }

    @PostMapping("/new")
    public RecipeCommand saveRecipe(@RequestBody RecipeCommand recipeCommand) {
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
