package br.rafanthx13.recipesserver.model.repository;

import br.rafanthx13.recipesserver.model.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
