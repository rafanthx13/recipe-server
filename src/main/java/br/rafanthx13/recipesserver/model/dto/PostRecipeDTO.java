package br.rafanthx13.recipesserver.model.dto;

import br.rafanthx13.recipesserver.model.entity.Badge;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostRecipeDTO {

    @NotEmpty
    private String title;
    private String imgSrc;
    private List<Badge> badges;
    private String ingredients;
    private String tab_prepare;
    private String tab_comments;
    private String tab_others;

}

/*
{
    "title": "titulo-post",
    "imgSrc": "fffff",
    "badges": "badges",
    "ingredientes": "ingredientes",
    "tab_prepare": "preparo",
    "tab_comments": "comentarios",
    "tab_others": "outras coisas"
}

{
    "title": "titulo-post",
    "imgSrc": "fffff",
    "badges": [
    {"id": 1, "tag": "Tag1"},
    {"id": 2, "tag": "tag3"}
    ],
    "ingredientes": "ingredientes",
    "tab_prepare": "preparo",
    "tab_comments": "comentarios",
    "tab_others": "outras coisas"
}

 */
