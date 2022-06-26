package com.example.languagedesign.commands;

import com.example.languagedesign.domain.Difficulty;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeCommand {
    private Long id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String image;
    private String directions;
    private Set<Long> ingredients= new HashSet<>();
    private Difficulty difficulty;
    private Set<Long> categories = new HashSet<>();
}
