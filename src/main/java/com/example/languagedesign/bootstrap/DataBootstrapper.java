package com.example.languagedesign.bootstrap;

import com.example.languagedesign.domain.*;
import com.example.languagedesign.repositories.CategoryRepository;
import com.example.languagedesign.repositories.IngredientRepository;
import com.example.languagedesign.repositories.RecipeRepository;
import com.example.languagedesign.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Value
@RequiredArgsConstructor
@Slf4j
@Component
public class DataBootstrapper implements CommandLineRunner {

    CategoryRepository categoryRepository;
    IngredientRepository ingredientRepository;

    UserRepository userRepository;
    RecipeRepository recipeRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String... args) throws Exception {

        var user = new CustomUser();
        user.setUsername("raspa");
        user.setPassword(bCryptPasswordEncoder.encode("password"));
        userRepository.save(user);

        loadCategories();
        loadRecipes(1l);
        loadRecipes(2l);
        loadRecipes(3l);
        loadRecipes(4l);

    }

    private void loadRecipes(Long categoryId) {
        var ingredients = new HashSet<Ingredient>();
        for (int i = 0; i < 10; i++) {
            var ingredient = new Ingredient();
            ingredient.setDescription("Lorem ipsum " + i);
            ingredient.setAmount(2.0);
            ingredient.setUnitOfMeasure("CUP");
            ingredient.setPrice(2.3);
            ingredient = ingredientRepository.save(ingredient);
            ingredients.add(ingredient);
        }

        var recipe = new Recipe();

        recipe.setDescription("Lorem ipsum");
        recipe.setPrepTime(1);
        recipe.setCookTime(2);
        recipe.setServings(3);
        recipe.setSource("Lorem ipsum");
        recipe.setIsApproved(true);
        recipe.setDirections("Lorem ipsum");
        recipe.setDifficulty(Difficulty.HARD);
        recipeRepository.save(recipe);
        ingredients.forEach(recipe::addIngredient);
        ingredientRepository.saveAll(ingredients);
        recipeRepository.save(recipe);
        var category = categoryRepository.findById(categoryId).get();
        category.addRecipe(recipe);
        recipeRepository.save(recipe);
        categoryRepository.save(category);


    }

    private void loadCategories() {
        log.debug("Bootstrapping categories");
        for (int i = 0; i < 10; i++) {

            var category = new Category();
            category.setDescription("Lorem ipsum " + i);
            categoryRepository.save(category);
        }
    }
}
