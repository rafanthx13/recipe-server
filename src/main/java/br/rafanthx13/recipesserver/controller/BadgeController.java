package br.rafanthx13.badgesserver.controller;

import br.rafanthx13.badgesserver.model.entity.Badge;
import br.rafanthx13.badgesserver.service.BadgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController 
@RequestMapping("/badges") 
public class BadgeController {

	@Autowired
	private BadgeService badgeService;

	@GetMapping
	public List<Badge> getAllBadges(){
		return badgeService.getAll();
	}

	@GetMapping("{id}")
	public Badge getBadgeById(@PathVariable Long id ){
		return badgeService.findById(id)
				.orElseThrow(() ->
						new ResponseStatusException(HttpStatus.NOT_FOUND,
								"Cliente não encontrado"));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void saveBadge(@RequestBody @Valid Badge badge){
		badgeService.save(badge);
	}

	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update( @PathVariable Long id,
						@RequestBody @Valid Badge badge ){
		badgeService
				.findById(id)
				.map( existBadge -> {
					badge.setId(existBadge.getId());
					badgeService.save(badge);
					return existBadge;
				}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				"Receita não encontrada") );
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id){
		Badge badge = badgeService
				.findById(id)
				.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND) );
		badgeService.delete(badge);
	}

}