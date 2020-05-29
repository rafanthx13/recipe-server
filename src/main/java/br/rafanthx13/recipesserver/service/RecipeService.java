package br.rafanthx13.recipesserver.service;

import java.util.List;
import java.util.Optional;

import br.rafanthx13.recipesserver.model.entity.Recipe;


public interface RecipeService {

  List<Recipe> getAll();

  Optional<Recipe> findById(Long id);

  Recipe save(Recipe recipe);

  void delete (Recipe recipe);
  
}