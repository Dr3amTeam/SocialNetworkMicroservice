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
    @Schema(example = "1", description = "ID de post")
    @Column(length = 36) @Getter @Setter
    private String postId;


    public CommentView() {}

    public CommentView(String commentId, String text, String postId) {
        this.commentId = commentId;
        this.text = text;
        this.postId = postId;
    }
}