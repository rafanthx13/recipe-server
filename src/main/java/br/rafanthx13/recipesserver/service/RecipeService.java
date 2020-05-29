package br.rafanthx13.recipesserver.service;

import java.util.List;

import br.rafanthx13.recipesserver.model.entity.Recipe;


public interface RecipeService {

  List<Recipe> geAll();
  
}