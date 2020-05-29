package br.rafanthx13.recipesserver.model.repository;

import br.rafanthx13.recipesserver.model.entity.Recipe;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

  
}