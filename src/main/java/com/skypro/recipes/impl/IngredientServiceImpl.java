package com.skypro.recipes.impl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skypro.recipes.model.Ingredient;
import com.skypro.recipes.model.Recipe;
import com.skypro.recipes.service.FilesService;
import com.skypro.recipes.service.IngredientService;
import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.nio.file.FileSystemException;
import java.nio.file.NoSuchFileException;
import java.util.Map;
import java.util.TreeMap;

@Service
public class IngredientServiceImpl implements IngredientService {
    private static Map<Long, Ingredient> ingredientsL = new TreeMap<>();
    private static Long idIng = 0L;
    private final FilesService filesService;

    public IngredientServiceImpl(FilesService filesService) {
        this.filesService = filesService;
    }

    @Override
    public Long addNewIngredient(Ingredient ingredient) {
        ingredientsL.putIfAbsent(idIng, ingredient);
        saveToFile1();
        return idIng++;
    }

    @Override
    public Ingredient getIngredient(Long idIng) {
        saveToFile1();
        return ingredientsL.get(idIng);
    }

    @Override
    public Map<Long, Ingredient> getAllIngredient() {
        return ingredientsL;
    }

    @Override
    public Ingredient putIngredient(Long idIng, Ingredient ingredient) {
        ingredientsL.putIfAbsent(idIng, ingredient);
        saveToFile1();
        return ingredient;
    }

    @Override
    public boolean deleteIngredient(Long idIng) {
        saveToFile1();
        return ingredientsL.remove(idIng) != null;
    }
    @Override
    public boolean deleteAllIngredient() {
        saveToFile1();
        ingredientsL = new TreeMap<>();
        return false;
    }

    // методы для json
    private void saveToFile1() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredientsL);
            filesService.saveToFile1(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile1() {
        String json = filesService.readFromFile1();
        try {
            ingredientsL = new ObjectMapper().readValue(json, new TypeReference<TreeMap<Long, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @PostConstruct
    private void init () {
        readFromFile1();
    }

}
