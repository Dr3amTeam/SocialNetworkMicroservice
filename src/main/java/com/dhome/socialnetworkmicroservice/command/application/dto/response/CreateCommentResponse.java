package com.dhome.socialnetworkmicroservice.command.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.util.Date;

@Value
public class CreateCommentResponse {
    @Schema(example = "1", description = "ID del comentario")
    private @Setter @Getter String comentId;
    @Schema(example = "Hola como estas", description = "Mensaje del comentario")
    private @Getter String message;

    @Schema(example = "1", description = "ID de la publicacion")
    private @Getter String postId;
}
