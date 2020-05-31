package br.rafanthx13.recipesserver.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table( name = "recipe" )
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "img_src", length = 200)
    private String imgSrc;

    @Column(name = "badges", length = 200)
    private String badges;

    @Column(name = "ingredients", length = 200)
    private String ingredients;

    @Column(name = "tab_prepare", length = 200)
    private String tab_prepare;

    @Column(name = "tab_comments", length = 200)
    private String tab_comments;

    @Column(name = "tab_others", length = 200)
    private String tab_others;

}

/*
{
    "title": "titulo-post",
    "imgSrc": "image",
    "badges": "badges",
    "ingredients": "ingredientes",
    "tab_prepare": "preparo",
    "tab_comments": "comment",
    "tab_others": "outras coisas"
}
 */