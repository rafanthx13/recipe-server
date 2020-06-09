package br.rafanthx13.recipesserver.model.repository;

import br.rafanthx13.recipesserver.model.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

   Boolean existsByTitle(String title);

  
}

/*
	@Query(" select p from Pedido p left join fetch p.itens where p.id = :id ")
    Optional<Pedido> findByIdFetchItens(@Param("id") Integer id);
*/