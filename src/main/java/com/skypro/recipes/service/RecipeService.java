package com.skypro.recipes.service;
import com.skypro.recipes.model.Recipe;

import java.util.Map;

public interface RecipeService {
    Recipe addNewRecipe(Recipe recipe);
    Recipe getRecipe(Long idRec);

    Map<Long, Recipe> getAllRecipe();

    Recipe putRecipe(Long idRec, Recipe recipe);

    boolean deleteRecipe(Long idRec);


}
