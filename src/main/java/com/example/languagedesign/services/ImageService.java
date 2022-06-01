package com.example.languagedesign.services;

import com.example.languagedesign.domain.Recipe;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    Recipe saveImageFile(Long recipeId, MultipartFile file);

}


