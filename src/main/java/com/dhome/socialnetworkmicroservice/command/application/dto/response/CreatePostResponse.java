package com.dhome.socialnetworkmicroservice.command.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.util.Date;

@Value
public class CreatePostResponse {
    @Schema(example = "1", description = "ID de la publicaion")
    private @Setter
    @Getter String postId;

    @Schema(example = "Esta es mi publicación", description = "Nombre del cliente")
    private @Getter String description;

    @Schema(example = "14/11/2020", description = "Fecha de publicación")
    private @Getter Date createdDate;
}