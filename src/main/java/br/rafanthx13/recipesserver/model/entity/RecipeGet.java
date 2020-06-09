package br.rafanthx13.recipesserver.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import br.rafanthx13.recipesserver.model.entity.Badge;

import java.util.List;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table( name = "recipe" )
public class RecipeGet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "img_src")
    private Long imgSrc;

    @Column(name = "ingredients", length = 200)
    private String ingredients;

    @Column(name = "tab_prepare", length = 200)
    private String tab_prepare;

    @Column(name = "tab_comments", length = 200)
    private String tab_comments;

    @Column(name = "tab_others", length = 200)
    private String tab_others;

    @Transient
    private String imgSource;

    @OneToMany()
    @JoinTable(name = "recipe_badge",
            joinColumns = {@JoinColumn(name = "id")},
            inverseJoinColumns = {@JoinColumn(name = "badge_id")})
    private List<Badge> badges;

}
