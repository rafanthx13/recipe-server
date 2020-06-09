package br.rafanthx13.recipesserver.controller;

import br.rafanthx13.recipesserver.model.entity.Badge;
import br.rafanthx13.recipesserver.service.BadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static br.rafanthx13.recipesserver.exception.ApiErrors.errorNotFound;

@RestController 
@RequestMapping("/badges") 
public class BadgeController {

	@Autowired
	private BadgeService badgeService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Badge> getAllBadges(){
		return badgeService.getAll();
	}

	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public Badge getBadgeById(@PathVariable Long id ){
		return badgeService.findById(id)
				.orElseThrow(() -> errorNotFound("Tag não encontrada"));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Badge saveBadge(@RequestBody @Valid Badge badge){
		return badgeService.save(badge);
	}

	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Badge update( @PathVariable Long id,
						@RequestBody @Valid Badge badge ){
		return badgeService
				.findById(id)
				.map( existBadge -> {
					badge.setId(existBadge.getId());
					badgeService.update(badge, id);
					return existBadge;
				}).orElseThrow(() -> errorNotFound("Tag não encontrada para editar") );
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id){
		Badge badge = badgeService
				.findById(id)
				.orElseThrow( () -> errorNotFound("Tag não encontrada") );
		badgeService.delete(badge);
	}

}