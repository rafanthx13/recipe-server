package br.rafanthx13.recipesserver.model.repository;

import br.rafanthx13.recipesserver.model.entity.Recipe;
import br.rafanthx13.recipesserver.model.entity.RecipeBadge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RecipeBadgeRepository extends JpaRepository<RecipeBadge, Long> {

//    @Query("SELECT t FROM Todo t where t.title = ?1 AND t.description = ?2")
    // public Optional<RecipeBadge> findByRecipe_idAndBadge_id(Long recipe_id, Long badge_id);
    @Modifying
    @Query(value = "delete from recipe_badge where recipe_id = :recipe_id", nativeQuery = true)
    public int deleteByRecipe_id(@Param("recipe_id") Long recipe_id);

//    @Modifying
    @Query(value = "select * from recipe_badge rb where rb.recipe_id = :recipe_id", nativeQuery = true)
    List<RecipeBadge> findReByRecipeId(@Param("recipe_id") Long recipe_id);

//     Long deleteByRecipe__id(Recipe recipe_id);
}
