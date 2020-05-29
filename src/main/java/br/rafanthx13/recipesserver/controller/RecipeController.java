package br.rafanthx13.recipesserver.controller;

import org.springframework.web.bind.annotation.*;

import br.rafanthx13.recipesserver.model.entity.Recipe;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController // Controlador Rest
@RequestMapping("/recipes") // Base URL
@RequiredArgsConstructor // Gera e injeta os objetos privados
public class RecipeController {

	@GetMapping
	public List<Recipe> getAllRecipes(){
		return null;
	}
}