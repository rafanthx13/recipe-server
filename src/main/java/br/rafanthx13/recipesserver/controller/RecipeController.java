package br.rafanthx13.recipesserver.controller;

import br.rafanthx13.recipesserver.model.dto.PostRecipeDTO;
import br.rafanthx13.recipesserver.model.entity.Recipe;
import br.rafanthx13.recipesserver.model.entity.RecipeGet;
import br.rafanthx13.recipesserver.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController // Controlador Rest
@RequestMapping("/recipes") // Base URL
@RequiredArgsConstructor // Gera e injeta os objetos privados
public class RecipeController {

	// private final RecipeService recipeService;
	@Autowired
	private RecipeService recipeService;

	// @@ Get que retorna Imagem também
	/*
	@GetMapping
	public List<Recipe> getAllRecipes(){
		return recipeService.getAll();
	}
	*/

    /*
        Como funciona Pageable
        + Se você não passa nenhum parâmetro mas pede um Pageable como no exemplo abaixo
            então será construído um Pageable por default : page = 0 , size = 20
        + As configurações do Pageable pela URL são
        	+ lembrando: query params começa com ? e para cada parâmetro coloca & entre eles
            +  ?size=number
            +  ?page=number
        + Usamos PageImpl<> para poder passa a quantidade total e elemento so select *
            mesmo que agente mostre8 items por pagina, andar a quantidade total de elementos

     */
	// TODO: Otimizar
    @GetMapping
    public Page<RecipeGet> getAllRecipesPageable(Pageable pageRequest){
        Pageable pageable = PageRequest.of(pageRequest.getPageNumber(), 8); // 8 ITEMS POR PAGINA
        Page<RecipeGet> listPages = recipeService.getAllPageable(pageable);
        return new PageImpl<>(listPages.getContent(), pageable, listPages.getTotalElements());
    }

//	@GetMapping()
//	public List<RecipeGet> getAllRecipesSrc(){
//		return recipeService.getAllRecipes();
//	}

	// TODO: Otimizar
	@GetMapping("{id}")
	public RecipeGet getRecipeById(@PathVariable Long id ){
		return recipeService.getById(id);
	}

//	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
//	public void saveRecipe(@RequestBody @Valid Recipe recipe){
//		recipeService.save(recipe);
//	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void saveRecipeRe(@RequestBody @Valid PostRecipeDTO postRecipeDTO){
		recipeService.saveRe(postRecipeDTO);
	}

	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateRe( @PathVariable Long id,
						@RequestBody @Valid PostRecipeDTO postRecipeDTO ){
		recipeService.updateRe(postRecipeDTO, id);
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id){
		Recipe recipe = recipeService
				.findById(id)
				.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND) );
		recipeService.delete(recipe);
	}

	// uncompress the image bytes before returning it to the angular application
    // public  byte[] decompressBytes(byte[] data) {
    //     Inflater inflater = new Inflater();
    //     inflater.setInput(data);
    //     ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
    //     byte[] buffer = new byte[1024];
    //     try {
    //         while (!inflater.finished()) {
    //             int count = inflater.inflate(buffer);
    //             outputStream.write(buffer, 0, count);
    //         }
    //         outputStream.close();
    //     } catch (IOException ioe) {
    //         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error IOException");
    //     } catch (DataFormatException e) {
    //         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error DataFormatException");
    //     }
    //     return outputStream.toByteArray();
    // }

	//	@PutMapping("{id}")
	//	@ResponseStatus(HttpStatus.NO_CONTENT)
	//	public void update( @PathVariable Long id,
	//						@RequestBody @Valid Recipe recipe ){
	//		recipeService
	//				.findById(id)
	//				.map( existRecipe -> {
	//					recipe.setId(existRecipe.getId());
	//					recipeService.save(recipe);
	//					return existRecipe;
	//				}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
	//				"Receita não encontrada") );
	//	}



}