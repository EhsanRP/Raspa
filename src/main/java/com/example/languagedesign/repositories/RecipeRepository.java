package com.example.languagedesign.repositories;

import com.example.languagedesign.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    Set<Recipe> findAllByIsApproved(boolean isApproved);
}
