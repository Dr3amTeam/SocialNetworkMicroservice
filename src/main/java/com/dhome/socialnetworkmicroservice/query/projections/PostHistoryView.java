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
public class PostHistoryView {
    @Schema(example = "1", description = "Número de historial de post")
    @Id @GeneratedValue @Getter @Setter
    private Long postHistoryId;
    @Schema(example = "1", description = "ID del post")
    @Column(length = 36) @Getter @Setter
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
    @Schema(example = "19/11/2021",description = "Fecha de creación")
    @Column(nullable = true) @Getter @Setter
    private Instant createdAt;
    @Schema(example = "21/11/2021",description = "Fecha de actualización")
    @Column(nullable = true) @Getter @Setter
    private Instant updatedAt;

    public PostHistoryView() {}

    public PostHistoryView(String postId, String videoUrl, String content, Date uploadDate, String employeeId, Instant createdAt, Instant updatedAt) {
        this.postId = postId;
        this.videoUrl = videoUrl;
        this.content = content;
        this.uploadDate = uploadDate;
        this.employeeId = employeeId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public PostHistoryView(PostHistoryView postHistoryView) {
        this.postId = postHistoryView.postId;
        this.videoUrl = postHistoryView.videoUrl;
        this.content = postHistoryView.content;
        this.uploadDate = postHistoryView.uploadDate;
        this.employeeId = postHistoryView.employeeId;
        this.createdAt = postHistoryView.createdAt;
        this.updatedAt = postHistoryView.updatedAt;
    }
}
