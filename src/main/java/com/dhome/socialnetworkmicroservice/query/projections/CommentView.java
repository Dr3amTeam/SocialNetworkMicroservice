package com.dhome.socialnetworkmicroservice.query.projections;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Data
@Entity
@Table(name = "comments")
public class CommentView {
    @Id @Column(length = 36) @Getter @Setter
    private String commentId;

    @Schema(example = "Holacomo estas", description = "mensaje")
    @Column(length = 100) @Getter @Setter
    private String message;


    @Schema(example = "1", description = "post id")
    @Column(length = 100, name = "post_id") @Getter @Setter
    private String postId;

    @Schema(example = "19/11/2021",description = "Fecha de creación")
    @Column(nullable = true) @Getter @Setter
    private Instant createdAt;

    @Schema(example = "21/11/2021",description = "Fecha de actualización")
    @Column(nullable = true) @Getter @Setter
    private Instant updatedAt;

    public CommentView() {
    }

    public CommentView(String commentId, String message, String postId, Instant createdAt, Instant updatedAt) {
        this.commentId = commentId;
        this.message = message;
        this.postId = postId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}