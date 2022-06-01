package com.example.languagedesign.services;

import com.example.languagedesign.domain.Recipe;
import com.example.languagedesign.exceptions.ResourceNotFoundException;
import com.example.languagedesign.repositories.RecipeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    @Override
    @Transactional
    public Recipe saveImageFile(Long recipeId, MultipartFile file) {
        log.trace("Handling save Image with name {}", file.getOriginalFilename());

        var recipeOptional = recipeRepository.findById(recipeId);
        if (recipeOptional.isEmpty()) {
            throw new ResourceNotFoundException("Recipe with ID " + recipeId + " Not Found");
        }
        var recipe = recipeOptional.get();

        try {

            Byte[] byteObjects = new Byte[file.getBytes().length];
            int i = 0;

            for (byte b : file.getBytes()) {
                byteObjects[i++] = b;
            }

            recipe.setImage(byteObjects);
            return recipeRepository.save(recipe);

        } catch (Exception e) {

        }

        return null;
    }
}
