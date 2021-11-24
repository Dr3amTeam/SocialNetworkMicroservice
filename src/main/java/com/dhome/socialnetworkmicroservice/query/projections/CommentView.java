package com.dhome.socialnetworkmicroservice.query.projections;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Entity
public class CommentView {
    @Schema(example = "1", description = "ID del comentario")
    @Id @Column(length = 36) @Getter @Setter
    private String commentId;
    @Schema(example = "Buen viaje", description = "Contenido del comentario")
    @Column(length = 300) @Getter @Setter
    private String text;
    @Schema(example = "1", description = "ID de comentador")
    @Column(length = 36) @Getter @Setter
    private String commenterId;
    @Schema(example = "1", description = "ID de post")
    @Column(length = 36) @Getter @Setter
    private String postId;
    @Schema(example = "19/11/2021",description = "Fecha de creación")
    @Column(nullable = true) @Getter @Setter
    private Instant createdAt;
    @Schema(example = "21/11/2021",description = "Fecha de actualización")
    @Column(nullable = true) @Getter @Setter
    private Instant updatedAt;

    public CommentView() {}

    public CommentView(String commentId, String text, String commenterId, String postId, Instant createdAt, Instant updatedAt) {
        this.commentId = commentId;
        this.text = text;
        this.commenterId = commenterId;
        this.postId = postId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}