package com.example.languagedesign.services;

import com.example.languagedesign.commands.IngredientCommand;
import com.example.languagedesign.commands.RecipeCommand;
import com.example.languagedesign.converters.IngredientConverter;
import com.example.languagedesign.converters.RecipeConverter;
import com.example.languagedesign.domain.Ingredient;
import com.example.languagedesign.exceptions.ResourceNotFoundException;
import com.example.languagedesign.repositories.CategoryRepository;
import com.example.languagedesign.repositories.IngredientRepository;
import com.example.languagedesign.repositories.RecipeRepository;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Value
@RequiredArgsConstructor
@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    RecipeConverter recipeConverter;
    IngredientConverter ingredientConverter;

    IngredientRepository ingredientRepository;
    RecipeRepository recipeRepository;
    CategoryRepository categoryRepository;

    @Override
    public Set<RecipeCommand> findAllRecipesByIsApproved(Boolean isApproved) {
        log.debug("Getting all recipes");
        var recipeList = new HashSet<>(recipeRepository.findAllByIsApproved(isApproved));
        return recipeConverter.CommandListMaker(recipeList);
    }

    @Override
    public Set<RecipeCommand> findAllRecipesByCategoryId(Long categoryId) {
        log.debug("Getting recipes in category with id {}", categoryId);

        var categoryOptional = categoryRepository.findById(categoryId);
        if (categoryOptional.isEmpty())
            throw new ResourceNotFoundException("Category with id " + categoryId + " not found");

        var recipes = categoryOptional.get().getRecipes();

        return recipeConverter.CommandListMaker(recipes);

    }

    @Override
    public RecipeCommand findRecipeById(Long id) {
        log.debug("Getting recipe with id {}", id);

        var recipeOptional = recipeRepository.findById(id);

        if (recipeOptional.isEmpty())
            throw new ResourceNotFoundException("Recipe with id " + id + " not found");

        return recipeConverter.commandMaker(recipeOptional.get());
    }

    @Override
    public RecipeCommand saveRecipe(RecipeCommand recipeCommand) {
        log.debug("Saving a new Recipe");

        var recipe = recipeConverter.entityMaker(recipeCommand);
        recipeCommand.getIngredientCommands()
                .stream()
                .map(x -> {
                    Ingredient ingredient = null;
                    try {
                        var ingredientOptional = ingredientRepository.findById(x);
                        if (ingredientOptional.isEmpty())
                            throw new ResourceNotFoundException("Ingredient with id " + x + " not fount");
                        ingredient = ingredientOptional.get();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return ingredient;
                })
                .peek(recipe::addIngredient);
        recipe = recipeRepository.save(recipe);
        return recipeConverter.commandMaker(recipe);
    }

    @Override
    public void deleteRecipe(Long id) {
        log.debug("Deleting recipe with id {}", id);

        var recipeOptional = recipeRepository.findById(id);
        if (recipeOptional.isEmpty())
            throw new ResourceNotFoundException("Recipe with id " + id + " not found");

        recipeRepository.deleteById(id);
    }

    @Override
    public Set<IngredientCommand> findRecipeIngredients(Long recipeId) {
        log.debug("Getting ingredients for recipe with id {}", recipeId);

        var recipeOptional = recipeRepository.findById(recipeId);
        if (recipeOptional.isEmpty())
            throw new ResourceNotFoundException("Recipe with id " + recipeId + " not found");

        var ingredientList = recipeOptional.get().getIngredients();
        return ingredientConverter.commandListMaker(ingredientList);
    }

    @Override
    public RecipeCommand approveRecipe(Long recipeId) {
        log.debug("Approving recipe with id {}", recipeId);

        var recipeOptional = recipeRepository.findById(recipeId);
        if (recipeOptional.isEmpty())
            throw new ResourceNotFoundException("Recipe with id " + recipeId + " not found");

        var recipe = recipeOptional.get();
        recipe.setIsApproved(true);

        recipe = recipeRepository.save(recipe);

        return recipeConverter.commandMaker(recipe);
    }
}
