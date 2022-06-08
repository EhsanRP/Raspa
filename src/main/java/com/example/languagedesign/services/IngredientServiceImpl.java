package com.example.languagedesign.services;

import com.example.languagedesign.commands.IngredientCommand;
import com.example.languagedesign.converters.IngredientConverter;
import com.example.languagedesign.exceptions.ResourceNotFoundException;
import com.example.languagedesign.repositories.IngredientRepository;
import com.example.languagedesign.repositories.RecipeRepository;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Value
@RequiredArgsConstructor
@Service
public class IngredientServiceImpl implements IngredientService {

    IngredientConverter ingredientConverter;
    RecipeRepository recipeRepository;
    IngredientRepository ingredientRepository;

    @Override
    public IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand) {
        log.debug("Saving a new Ingredient");


        var ingredient = ingredientConverter.entityMaker(ingredientCommand);
        ingredient.setRecipe(null);
        var savedIngredient = ingredientRepository.save(ingredient);

        return ingredientConverter.commandMaker(savedIngredient);
    }

    @Override
    public void deleteById(Long ingredientId) {
        log.debug("Deleting ingredient with id {}", ingredientId);

        var ingredientOptional = ingredientRepository.findById(ingredientId);
        if (ingredientOptional.isEmpty())
            throw new ResourceNotFoundException("Ingredient with id " + ingredientId + " not found");

        ingredientRepository.deleteById(ingredientId);
    }

    @Override
    public Set<IngredientCommand> findByRecipeId(Long recipeId) {
        log.debug("Finding recipe ingredients with recipe id {}", recipeId);
        var ingredientList = ingredientRepository.findAllByRecipe_Id(recipeId);
        return ingredientConverter.commandListMaker(ingredientList);
    }
}
