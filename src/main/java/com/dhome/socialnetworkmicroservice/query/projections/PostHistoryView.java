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
    @Schema(example = "1", description = "Número de historial de publicacion")
    @Id @GeneratedValue @Getter @Setter private Long postHistoryId;

    @Schema(example = "1", description = "Id de la publicacion")
    @Column(length = 36) @Getter @Setter private String postId;

    @Schema(example = "Hola buenas tardes", description = "Descripcion")
    @Column(length = 36) @Getter @Setter private String descripcion;

    @Schema(example = "12/12/12", description = "Fecha de creacion")
    @Column(length = 50) @Getter @Setter private Date createdDate;

    @Schema(example = "1",description = "Employee Id")
    @Column(length = 50) @Getter @Setter private String employeeId;

    @Schema(example = "19/11/2021",description = "Fecha de creación")
    @Column(nullable = true) @Getter @Setter
    private Instant createdAt;

    @Schema(example = "21/11/2021",description = "Fecha de actualización")
    @Column(nullable = true) @Getter @Setter
    private Instant updatedAt;

    public PostHistoryView(){

    }

    public PostHistoryView(String postId, String descripcion, Date createdDate, String employeeId, Instant createdAt, Instant updatedAt){
        this.postId = postId;
        this.descripcion = descripcion;
        this.createdDate = createdDate;
        this.employeeId = employeeId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public PostHistoryView(PostHistoryView postHistoryView){
        this.postId = postHistoryView.postId;
        this.descripcion = postHistoryView.descripcion;
        this.createdDate = postHistoryView.createdDate;
        this.employeeId = postHistoryView.employeeId;
        this.createdAt = postHistoryView.createdAt;
        this.updatedAt = postHistoryView.updatedAt;
    }
}
