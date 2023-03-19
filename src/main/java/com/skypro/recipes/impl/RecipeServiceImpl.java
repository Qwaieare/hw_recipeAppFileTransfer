package com.skypro.recipes.impl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skypro.recipes.model.Recipe;
import com.skypro.recipes.service.FilesService;
import com.skypro.recipes.service.RecipeService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.TreeMap;

@Service
public class RecipeServiceImpl implements RecipeService {

    private static Map<Long, Recipe> recipeL = new TreeMap<>();
    private static Long idRec = 0L;
    private final FilesService filesService;

    public RecipeServiceImpl(FilesService filesService) {
        this.filesService = filesService;
    }

    @Override
    public Long addNewRecipe(Recipe recipe) {
        recipeL.putIfAbsent(idRec, recipe);
        saveToFile();
        return idRec++;
    }

    @Override
    public Recipe getRecipe(Long idRec) {
        return recipeL.get(idRec);
    }

    @Override
    public Map<Long, Recipe> getAllRecipe() {
        return recipeL;
    }

    @Override
    public Recipe putRecipe(Long idRec, Recipe recipe) {
        recipeL.putIfAbsent(idRec, recipe);
        saveToFile();
        return recipe;
    }

    @Override
    public boolean deleteRecipe(Long idRec) {
        saveToFile();
        return  recipeL.remove(idRec) != null;
    }

    @Override
    public boolean deleteAllRecipe() {
        recipeL = new TreeMap<>();
        saveToFile();
        return false;
    }

    // методы для json
    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipeL);
            filesService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        String json = filesService.readFromFile();
        try {
            recipeL = new ObjectMapper().readValue(json, new TypeReference<TreeMap<Long, Recipe>>() {
            });
        } catch (JsonMappingException e) {
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @PostConstruct
    private void init () {
        readFromFile();
    }
}





