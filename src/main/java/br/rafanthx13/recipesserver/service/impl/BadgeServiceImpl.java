package br.rafanthx13.recipesserver.service.impl;

import br.rafanthx13.recipesserver.model.repository.BadgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.rafanthx13.recipesserver.model.entity.Badge;
import br.rafanthx13.recipesserver.service.BadgeService;

import java.util.List;
import java.util.Optional;


@Service
public class BadgeServiceImpl implements BadgeService {

  @Autowired
  private BadgeRepository badgeRepository;

  @Override
  public List<Badge> getAll() {
    return badgeRepository.findAll();
  }

  @Override
  public Optional<Badge> findById(Long id) {
    return badgeRepository.findById(id);
//    return Optional.empty();
  }

  @Override
  public Badge save(Badge badge) {
    return badgeRepository.save(badge);
  }

  @Override
  public void delete(Badge badge) {
    badgeRepository.delete(badge);
  }

}