package br.rafanthx13.recipesserver.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "recipe_badge")
public class RecipeBadge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "badge_id")
    private Badge badge_id;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe_id;

}
