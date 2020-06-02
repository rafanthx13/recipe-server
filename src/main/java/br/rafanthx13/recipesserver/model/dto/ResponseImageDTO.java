package br.rafanthx13.recipesserver.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseImageDTO {

    private Long imageId;
    private Boolean repeat;
}
