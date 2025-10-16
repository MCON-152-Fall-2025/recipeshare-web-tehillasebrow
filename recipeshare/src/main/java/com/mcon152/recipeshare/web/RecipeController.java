package com.mcon152.recipeshare.web;

import com.mcon152.recipeshare.Recipe;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    private final List<Recipe> recipes = new ArrayList<>();

    private final AtomicLong counter = new AtomicLong();
    @PostMapping
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        recipe.setId(counter.incrementAndGet());
        recipes.add(recipe);
        return recipe;
    }

    @GetMapping
    public List<Recipe> getAllRecipes() {
        return recipes;
    }

    @GetMapping("/{id}")
    public Recipe getRecipeById(@PathVariable long id) {
        for (Recipe recipe : recipes) {
            if (recipe.getId() == id) {
                return recipe;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public boolean deleteRecipe(@PathVariable long id) {
        for (int i = 0; i < recipes.size(); i++) {
            if (recipes.get(i).getId() == id) {
                recipes.remove(i);
                return true;
            }
        }
        return false;
    }
}