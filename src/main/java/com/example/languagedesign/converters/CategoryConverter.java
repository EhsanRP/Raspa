package com.example.languagedesign.converters;

import com.example.languagedesign.commands.CategoryCommand;
import com.example.languagedesign.domain.Category;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CategoryConverter {

    public CategoryCommand commandMaker(Category category) {
        var command = CategoryCommand
                .builder()
                .id(category.getId())
                .description(category.getDescription())
                .build();

        return command;
    }

    public Set<CategoryCommand> commandListMaker(Set<Category> categoryList) {
        var commandList = new HashSet<CategoryCommand>();
        for (Category category : categoryList) {
            var command = commandMaker(category);
            commandList.add(command);
        }
        return commandList;
    }

    public Category entityMaker(CategoryCommand categoryCommand) {
        var category = Category
                .builder()
                .description(categoryCommand.getDescription())
                .build();

        if (categoryCommand.getId() != null)
            category.setId(categoryCommand.getId());

        return category;
    }
}
