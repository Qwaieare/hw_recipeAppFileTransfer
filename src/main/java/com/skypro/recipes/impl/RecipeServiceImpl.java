package com.skypro.recipes.impl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skypro.recipes.model.Recipe;
import com.skypro.recipes.service.FilesService;
import com.skypro.recipes.service.RecipeService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Service
    public class RecipeServiceImpl implements RecipeService {

    private static Map<Long, Recipe> recipeL = new TreeMap<>();
    private static Long idRec = 0L;
     final private FilesService filesService;

    public RecipeServiceImpl(FilesService filesService) {
        this.filesService = filesService;
    }

    @Override
    public Recipe addNewRecipe(Recipe recipe) {
        recipeL.putIfAbsent(idRec++, recipe);
        return recipe;
    }

    @Override
    public Recipe getRecipe(Long idRec) {
         return recipeL.get(idRec);
    }

    @Override
    public Map<Long, Recipe> getAllRecipe() {
        saveToFile();
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
         return recipeL.remove(idRec) != null;
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
     String json =  filesService.readFromFile();
        try {
            recipeL = new ObjectMapper().readValue(json, new TypeReference<TreeMap<Long, Recipe>>() {
         });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    @PostConstruct
    private void init() {
        readFromFile();
    }

}

