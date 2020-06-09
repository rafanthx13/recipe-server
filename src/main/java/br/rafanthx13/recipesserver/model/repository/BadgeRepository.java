package br.rafanthx13.recipesserver.model.repository;

import br.rafanthx13.recipesserver.model.entity.Badge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BadgeRepository extends JpaRepository<Badge, Long> {

    boolean existsByTag(String nome);

    Optional<Badge> findByTag(String nome);

}