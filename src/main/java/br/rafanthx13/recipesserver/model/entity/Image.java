package br.rafanthx13.recipesserver.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "files")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_name", length = 200)
    private String file_name;

    @Column(name = "file_type", length = 200)
    private String file_type;

    @Lob
    private byte[] data;
}