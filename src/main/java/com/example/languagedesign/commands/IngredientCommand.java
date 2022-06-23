package com.example.languagedesign.commands;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IngredientCommand {
    private Long id;
    private String description;
    private Double amount;
    private String unitOfMeasure;

    private Long recipeId;
}
