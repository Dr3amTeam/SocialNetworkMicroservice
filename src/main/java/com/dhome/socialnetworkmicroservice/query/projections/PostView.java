package com.dhome.socialnetworkmicroservice.query.projections;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;
import java.util.Date;

@Entity
public class PostView {
    @Schema(example = "1", description = "ID del post")
    @Id @Column(length = 36) @Getter @Setter
    private String postId;
    @Schema(example = "https://youtu.be/RSmwdijv_M0", description = "URL de video")
    @Column(length = 50) @Getter @Setter
    private String videoUrl;
    @Schema(example = "No estaré en casa", description = "Contenido de la publicación")
    @Column(length = 300) @Getter @Setter
    private String content;
    @Schema(example = "02/09/2021", description = "Fecha de subida")
    @Column(length = 10) @Getter @Setter
    private Date uploadDate;
    @Schema(example = "992", description = "ID de empleador")
    @Column(length = 36) @Getter @Setter
    private String employeeId;

    public PostView() {}

    public PostView(String postId, String videoUrl, String content, Date uploadDate, String employeeId) {
        this.postId = postId;
        this.videoUrl = videoUrl;
        this.content = content;
        this.uploadDate = uploadDate;
        this.employeeId = employeeId;

    }
}