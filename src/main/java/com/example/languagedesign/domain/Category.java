package com.example.languagedesign.domain;


import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToMany(mappedBy = "categories", fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<Recipe> recipes = new HashSet<>();


    public Category addRecipe(Recipe recipe) {
        recipe.getCategories().add(this);
        recipes.add(recipe);
        return this;
    }

}
