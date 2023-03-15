package com.skypro.recipes.service;
import com.skypro.recipes.model.Ingredient;

import java.util.Map;

public interface IngredientService {
    Ingredient addNewIngredient(Ingredient ingredient);
    Ingredient getIngredient(Long idIng);

    Map<Long, Ingredient> getAllIngredient();

    Ingredient putIngredient(Long idIng, Ingredient ingredient);

    boolean deleteIngredient(Long idIng);
}
