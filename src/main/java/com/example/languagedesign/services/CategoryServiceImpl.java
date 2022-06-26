package com.example.languagedesign.services;

import com.example.languagedesign.commands.CategoryCommand;
import com.example.languagedesign.commands.RecipeCommand;
import com.example.languagedesign.converters.CategoryConverter;
import com.example.languagedesign.converters.RecipeConverter;
import com.example.languagedesign.exceptions.ResourceNotFoundException;
import com.example.languagedesign.repositories.CategoryRepository;
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
public class CategoryServiceImpl implements CategoryService {

    CategoryConverter categoryConverter;
    RecipeConverter recipeConverter;
    RecipeRepository recipeRepository;
    CategoryRepository categoryRepository;

    @Override
    public Set<CategoryCommand> findAllCategories() {
        log.debug("Getting all categories");
        var categoryList = new HashSet<>(categoryRepository.findAll());
        return categoryConverter.commandListMaker(categoryList);
    }

    @Override
    public CategoryCommand findCategoryById(Long id) {
        log.debug("Getting category with id {}", id);

        var categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isEmpty())
            throw new ResourceNotFoundException("Category with id " + id + " not found");

        return categoryConverter.commandMaker(categoryOptional.get());
    }

    @Override
    public CategoryCommand saveCategory(CategoryCommand categoryCommand) {
        log.debug("Saving a new Category");
        var category = categoryConverter.entityMaker(categoryCommand);
        category = categoryRepository.save(category);
        return categoryConverter.commandMaker(category);
    }

    @Override
    public RecipeCommand assignRecipe(Long categoryId, Long recipeId) {
        log.debug("Assigning recipe with id {} to category with id {}", recipeId, categoryId);

        var recipeOptional = recipeRepository.findById(recipeId);
        if (recipeOptional.isEmpty())
            throw new ResourceNotFoundException("Recipe with id " + recipeId + " not found");


        var categoryOptional = categoryRepository.findById(categoryId);
        if (categoryOptional.isEmpty())
            throw new ResourceNotFoundException("Recipe with id " + categoryId + " not found");

        var recipe = recipeOptional.get();
        var category = categoryOptional.get();

        category.addRecipe(recipe);

        recipe = recipeRepository.save(recipe);
        categoryRepository.save(category);

        return recipeConverter.commandMaker(recipe);
    }

    @Override
    public void deleteCategory(Long id) {
        log.debug("Deleting category with id {}", id);

        var categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isEmpty())
            throw new ResourceNotFoundException("Category with id " + id + " not found");

        var category = categoryRepository.findById(id).get();

        if (category.getRecipes().size() > 1){
            var recipes = category.getRecipes().stream().peek(recipe -> recipe.getCategories().remove(category)).collect(Collectors.toList());
            recipeRepository.saveAll(recipes);
        }
        categoryRepository.deleteById(id);
    }
}
