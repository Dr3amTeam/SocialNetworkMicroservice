package com.dhome.socialnetworkmicroservice.command.application.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.Date;


public class CreatePostRequest {
    @Schema(example = "https://youtu.be/RSmwdijv_M0", description = "URL de video")
    private @Getter String videoUrl;
    @Schema(example = "No estaré en casa", description = "Contenido de la publicación")
    private @Getter String content;
    @Schema(example = "2021-11-20T09:39:57.438534Z", description = "Fecha de subida")
    private @Getter Date uploadDate;
    @Schema(example = "ae5c37de-7735-4964-b53a-2e3b0b901678", description = "ID de empleador")
    @NotNull
    private @Getter String employeeId;
}