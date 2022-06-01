package com.example.languagedesign.commands;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryCommand {
    private Long id;
    private String description;
}
