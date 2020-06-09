package br.rafanthx13.recipesserver.service;

import java.util.List;
import java.util.Optional;

import br.rafanthx13.recipesserver.model.entity.Badge;

public interface BadgeService {

  List<Badge> getAll();

  Optional<Badge> findById(Long id);

  Badge save(Badge badge);

  Badge update(Badge badge, Long Id);

  void delete (Badge badge);
  
}