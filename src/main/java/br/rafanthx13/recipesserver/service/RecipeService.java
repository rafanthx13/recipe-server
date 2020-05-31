package br.rafanthx13.recipesserver.service;

import java.util.List;
import java.util.Optional;

import br.rafanthx13.recipesserver.model.dto.PostRecipeDTO;
import br.rafanthx13.recipesserver.model.entity.Recipe;


public interface RecipeService {

  List<Recipe> getAll();

  Optional<Recipe> findById(Long id);

  List<PostRecipeDTO> getAllRecipe();

  Recipe save(Recipe recipe);

  Recipe saveRe(PostRecipeDTO recipe);

  void delete (Recipe recipe);
  
}