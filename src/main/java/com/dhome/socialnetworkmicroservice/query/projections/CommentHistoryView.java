package com.dhome.socialnetworkmicroservice.query.projections;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;
import java.util.Date;

@Entity
public class CommentHistoryView {
    @Schema(example = "1", description = "Número de historial de comentario")
    @Id @GeneratedValue @Getter @Setter
    private Long commentHistoryId;
    @Schema(example = "1", description = "ID del comentario")
    @Column(length = 36) @Getter @Setter
    private String commentId;
    @Schema(example = "Buen viaje", description = "Contenido del comentario")
    @Column(length = 300) @Getter @Setter
    private String text;
    @Schema(example = "1", description = "ID de post")
    @Column(length = 36) @Getter @Setter
    private String postId;


    public CommentHistoryView() {}

    public CommentHistoryView(String commentId, String text, String postId) {
        this.commentId = commentId;
        this.text = text;
        this.postId = postId;
    }

    public CommentHistoryView(CommentHistoryView commentHistoryView) {
        this.commentId = commentHistoryView.commentId;
        this.text = commentHistoryView.text;
        this.postId = commentHistoryView.postId;
    }
}
