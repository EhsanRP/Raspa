package com.example.languagedesign.bootstrap;

import com.example.languagedesign.domain.Category;
import com.example.languagedesign.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Value
@RequiredArgsConstructor
@Slf4j
@Component
public class DataBootstrapper implements CommandLineRunner {

    CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {

        log.debug("Bootstrapping categories");
        for (int i = 0; i < 10; i++) {

            var category = new Category();
            category.setDescription("Lorem ipsum " + i);
            categoryRepository.save(category);
        }
    }
}
