package br.rafanthx13.recipesserver.model.repository;

import br.rafanthx13.recipesserver.model.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {

    Optional<Image> findByFileName(String name);

    @Query(value = "select i.file_name from image i where i.id=:id", nativeQuery = true)
    Optional<String> findOnlyNameById(Long id);

}
