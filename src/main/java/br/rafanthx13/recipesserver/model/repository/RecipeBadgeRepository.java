package br.rafanthx13.recipesserver.model.repository;

import br.rafanthx13.recipesserver.model.entity.RecipeBadge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeBadgeRepository extends JpaRepository<RecipeBadge, Long> {

}
