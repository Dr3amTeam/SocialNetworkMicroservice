package com.dhome.socialnetworkmicroservice.command.application.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.util.Date;

@Value
public class CreatePostRequest {
    @Schema(example = "https://youtu.be/RSmwdijv_M0", description = "URL de video")
    private String videoUrl;
    @Schema(example = "No estaré en casa", description = "Contenido de la publicación")
    private String content;
    @Schema(example = "02/09/2021", description = "Fecha de subida")
    private Date uploadDate;
    @Schema(example = "0992", description = "ID de empleador")
    private String employeeId;
}