package br.rafanthx13.recipesserver.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_name", length = 200)
    private String fileName;

    @Column(name = "file_type", length = 200)
    private String fileType;

    @Lob
    private byte[] data;

    public Image(String originalFilename, String contentType, byte[] compressBytes) {
        this.fileName = originalFilename;
        this.fileType = contentType;
        this.data = compressBytes;
    }
}