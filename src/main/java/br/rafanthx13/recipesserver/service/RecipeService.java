package br.rafanthx13.recipesserver.service;

import java.util.List;
import java.util.Optional;

import br.rafanthx13.recipesserver.model.dto.PostRecipeDTO;
import br.rafanthx13.recipesserver.model.entity.Recipe;
import br.rafanthx13.recipesserver.model.entity.RecipeGet;


public interface RecipeService {

  List<Recipe> getAll();

  RecipeGet getById(Long id);

  // List<PostRecipeDTO> getAllRecipe();

  Optional<Recipe> findById(Long id);

  Recipe save(Recipe recipe);

  Recipe saveRe(PostRecipeDTO recipe);

  void delete (Recipe recipe);

  void updateRe(PostRecipeDTO postRecipeDTO, Long id);

  List<RecipeGet> getAllRecipes();
  
}