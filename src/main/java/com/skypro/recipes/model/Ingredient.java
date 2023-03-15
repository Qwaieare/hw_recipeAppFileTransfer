package com.skypro.recipes.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ingredient {

    private String nameIngredient;
    private int number;
    private String measurement;


}
