package com.dhome.socialnetworkmicroservice.command.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.util.Date;

@Value
public class CreateCommentRequest {
    @Schema(example = "Buen viaje", description = "Contenido del comentario")
    private String text;
    @Schema(example = "1", description = "ID del comentador")
    private String commenterId;
    @Schema(example = "1", description = "ID del post")
    private String postId;
}
