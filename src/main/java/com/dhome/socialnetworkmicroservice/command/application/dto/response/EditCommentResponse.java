package com.dhome.socialnetworkmicroservice.command.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Value
public class EditCommentResponse {
    @Schema(example = "1", description = "ID del comentario")
    private String commentId;
    @Schema(example = "Buen viaje", description = "Contenido del comentario")
    private String text;
    @Schema(example = "1", description = "ID del comentador")
    private String commenterId;
    @Schema(example = "1", description = "ID del post")
    private String postId;
}