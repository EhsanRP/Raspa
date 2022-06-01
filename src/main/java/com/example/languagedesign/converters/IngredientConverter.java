package com.example.languagedesign.converters;

import com.example.languagedesign.commands.IngredientCommand;
import com.example.languagedesign.domain.Ingredient;
import com.example.languagedesign.domain.Recipe;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class IngredientConverter {

    public IngredientCommand commandMaker(Ingredient ingredient) {
        var command = IngredientCommand
                .builder()
                .id(ingredient.getId())
                .description(ingredient.getDescription())
                .amount(ingredient.getAmount())
                .unitOfMeasure(ingredient.getUnitOfMeasure())
                .recipeId(ingredient.getRecipe().getId())
                .build();

        return command;
    }

    public Set<IngredientCommand> commandListMaker(Set<Ingredient> ingredientList) {
        var commandList = new HashSet<IngredientCommand>();
        for (Ingredient ingredient : ingredientList) {
            var command = commandMaker(ingredient);
            commandList.add(command);
        }
        return commandList;
    }

    public Ingredient entityMaker(IngredientCommand ingredientCommand) {
        var ingredient = Ingredient
                .builder()
                .description(ingredientCommand.getDescription())
                .amount(ingredientCommand.getAmount())
                .unitOfMeasure(ingredientCommand.getUnitOfMeasure())
                .build();

        if (ingredientCommand.getId() != null)
            ingredient.setId(ingredientCommand.getId());


        if (ingredientCommand.getRecipeId() != null) {
            var recipe = new Recipe();
            recipe.setId(ingredientCommand.getRecipeId());
            recipe.addIngredient(ingredient);
        }

        return ingredient;
    }
}
