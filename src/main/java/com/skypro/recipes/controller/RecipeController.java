package com.skypro.recipes.controller;
import com.skypro.recipes.model.Recipe;
import com.skypro.recipes.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/Recipe")
@Tag(name = "Рецепты", description = "Описание рецептов")
public class RecipeController {
    private RecipeService recipeService;


    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/getrecipe")
    public Recipe getRecipe(@RequestParam Long idRec) {
        return recipeService.getRecipe(idRec);
    }

    @PostMapping("/addrecipe")
    public void addNewRecipe(@RequestParam Recipe recipe) {
        recipeService.addNewRecipe(recipe);
    }


    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепты были найдены",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                            )
                    }
            )
    }

   )
     public  ResponseEntity<Map<Long, Recipe>> getAllRecipe()  {
        Map<Long, Recipe> recipeL = recipeService.getAllRecipe();
        if (recipeL == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipeL);
    }

    @PutMapping
    @Parameters(value = {
            @Parameter(name = "idRec", example = "recipe")
    }
  )
    public ResponseEntity<Recipe> putRecipe(@PathVariable Long idRec, @RequestBody Recipe recipe) {
        Recipe recipe1 = recipeService.putRecipe(idRec, recipe);
        if (recipe1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }

    @DeleteMapping("/idRec")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long idRec) {
        if (recipeService.deleteRecipe(idRec)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


}
