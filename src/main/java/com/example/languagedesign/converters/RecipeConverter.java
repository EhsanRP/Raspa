package com.example.languagedesign.converters;

import com.example.languagedesign.commands.RecipeCommand;
import com.example.languagedesign.domain.Category;
import com.example.languagedesign.domain.Ingredient;
import com.example.languagedesign.domain.Recipe;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Value
@RequiredArgsConstructor
@Component
public class RecipeConverter {

    CategoryConverter categoryConverter;
    IngredientConverter ingredientConverter;

    public RecipeCommand commandMaker(Recipe recipe) {
        var command = RecipeCommand
                .builder()
                .id(recipe.getId())
                .description(recipe.getDescription())
                .prepTime(recipe.getPrepTime())
                .cookTime(recipe.getCookTime())
                .servings(recipe.getServings())
                .source(recipe.getSource())
                .image(recipe.getImage())
                .directions(recipe.getDirections())
                .difficulty(recipe.getDifficulty())
                .build();

        if (recipe.getIngredients() != null && recipe.getIngredients().size() > 0)
            command.setIngredientCommands(recipe.getIngredients().stream().map(Ingredient::getId).collect(Collectors.toSet()));

        if (recipe.getCategories() != null && recipe.getCategories().size() > 0)
            command.setCategories(recipe.getCategories().stream().map(Category::getId).collect(Collectors.toSet()));

        return command;
    }

    public Set<RecipeCommand> CommandListMaker(Set<Recipe> recipes) {
        var commandList = new HashSet<RecipeCommand>();
        for (Recipe recipe : recipes) {
            var command = commandMaker(recipe);
            commandList.add(command);
        }
        return commandList;
    }

    public Recipe entityMaker(RecipeCommand recipeCommand) {
        var recipe = new Recipe();

        recipe.setDescription(recipeCommand.getDescription());
        recipe.setPrepTime(recipeCommand.getPrepTime());
        recipe.setCookTime(recipeCommand.getCookTime());
        recipe.setServings(recipeCommand.getServings());
        recipe.setSource(recipeCommand.getSource());
        recipe.setImage(recipeCommand.getImage());
        recipe.setDirections(recipeCommand.getDirections());
        recipe.setDifficulty(recipeCommand.getDifficulty());

        if (recipeCommand.getId() != null)
            recipe.setId(recipeCommand.getId());

        return recipe;
    }
}
