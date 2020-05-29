package br.rafanthx13.recipesserver.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Data
// @AllArgsConstructor
// @NoArgsConstructor
// public class ResponseRecipeDTO {

// }


// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// import java.math.BigDecimal;
// import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseRecipeDTO {

    private Long id;
    private String title;
    private String imgSrc;
    private List<String> badges;
    private String ingredients;
    private String tab_prepare;
    private String tab_comments;
    private String tab_others;

}

	/*
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    */

