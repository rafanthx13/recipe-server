package br.rafanthx13.recipesserver.controller;

import br.rafanthx13.recipesserver.model.entity.Recipe;
import br.rafanthx13.recipesserver.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController // Controlador Rest
@RequestMapping("/recipes") // Base URL
@RequiredArgsConstructor // Gera e injeta os objetos privados
public class RecipeController {

	// private final RecipeService recipeService;
	@Autowired
	private RecipeService recipeService;

	@GetMapping
	public List<Recipe> getAllRecipes(){
		return recipeService.getAll();
	}

	@GetMapping("{id}")
	public Recipe getRecipeById(@PathVariable Long id ){
		return recipeService.findById(id)
				.orElseThrow(() ->
						new ResponseStatusException(HttpStatus.NOT_FOUND,
								"Cliente não encontrado"));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void saveRecipe(@RequestBody @Valid Recipe recipe){
		recipeService.save(recipe);
	}

	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update( @PathVariable Long id,
						@RequestBody @Valid Recipe recipe ){
		recipeService
				.findById(id)
				.map( existRecipe -> {
					recipe.setId(existRecipe.getId());
					recipeService.save(recipe);
					return existRecipe;
				}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				"Receita não encontrada") );
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	// @ApiOperation("Deletes a book by id")
	public void delete(@PathVariable Long id){
		// log.info(" deleting book of id: {} ", id);
		Recipe recipe = recipeService
				.findById(id)
				.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND) );
		recipeService.delete(recipe);
	}

}