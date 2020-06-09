package br.rafanthx13.recipesserver.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import br.rafanthx13.recipesserver.model.entity.Badge;

import java.util.List;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;

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

    /* Funciona
    http://www.universidadejava.com.br/materiais/jpa-manytomany/
    Uma Recipe virá com várias Badges no atributo list_badges: [] ou [{id, tag}]
    Referencia a tabela [name = recipe_badge] sendo que se for fazer joins, use id com recipe_id
    + Funciona com Get: quando REcipeRepository bai buscar uma Recipe ele vai buscar pelo id em
        "recipe_badge" no attr "recipe_id" encontrar "badge_id" e retornar uma lista de Badges
    + Funciona com POST: Se tiver list_badges, ele faz outras inserçôes na tabela intermediario, 
    + OU seja, ele faz o quivalente há ...
    SELECT b.tag
    FROM recipe r
    LEFT JOIN recipe_badge rb ON r.id = rb.recipe_id
    LEFT JOIN badge b ON b.id = rb.badge_id
    WHERE r.id = 2;
    + Nâo precisou colocar nada em Badge

    */
    @OneToMany(fetch = FetchType.LAZY, cascade=ALL )
    @JoinTable(name="recipe_badge",
             joinColumns={@JoinColumn(name="id")},
             inverseJoinColumns={@JoinColumn(name="badge_id")})
    private List<Badge> badges;

    @OneToOne(fetch = FetchType.LAZY)
//    @JoinTable(name="image",
//            joinColumns = {@JoinColumn(name="id", referencedColumnName="img_src"),
//            }
//    )
    // O hibernate nao aceita dois mapemaento á 'img_src' entao, um deles tem que ter  insertable=false, updatable=false
    @JoinColumn(name = "img_src", referencedColumnName = "id", insertable=false, updatable=false)
    private Image image;

}

/*

{
    "title": "titulo-post88888",
    "imgSrc": 1,
    "badges": [{"id":1,"tag":"Tag1"}],
    "ingredients": "ingredientes",
    "tab_prepare": "preparo",
    "tab_comments": "comment",
    "tab_others": "outras coisas"
}


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

/*
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(name = "data_pedido")
    private LocalDate dataPedido;

    @Column(name = "total", precision = 20, scale = 2)
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusPedido status;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;

}

*/