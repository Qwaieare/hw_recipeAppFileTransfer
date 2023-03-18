package com.skypro.recipes.impl;
import com.skypro.recipes.model.Ingredient;
import com.skypro.recipes.service.IngredientService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {
    private static final Map<Long, Ingredient> ingredientsL = new LinkedHashMap<>();
    private static Long idIng = 0L;

    @Override
    public Long addNewIngredient(Ingredient ingredient) {
        ingredientsL.putIfAbsent(idIng, ingredient);
        return idIng++;
    }

    @Override
    public Ingredient getIngredient(Long idIng) {
        return ingredientsL.get(idIng);
    }

    @Override
    public Map<Long, Ingredient> getAllIngredient() {
        return ingredientsL;
    }

    @Override
    public Ingredient putIngredient(Long idIng, Ingredient ingredient) {
        ingredientsL.putIfAbsent(idIng, ingredient);
        return ingredient;
    }
    @Override
    public boolean deleteIngredient(Long idIng) {
            return ingredientsL.remove(idIng) != null;
    }

    // методы для json

}
