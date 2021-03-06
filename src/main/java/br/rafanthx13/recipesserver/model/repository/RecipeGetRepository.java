package br.rafanthx13.recipesserver.model.repository;

import br.rafanthx13.recipesserver.model.entity.RecipeGet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeGetRepository extends JpaRepository<RecipeGet, Long> {

    Page<RecipeGet> findAll(Pageable pageable);

}
