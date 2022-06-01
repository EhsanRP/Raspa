package com.example.languagedesign.repositories;

import com.example.languagedesign.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Set<Ingredient> findAllByRecipe_Id(Long recipeId);
}
