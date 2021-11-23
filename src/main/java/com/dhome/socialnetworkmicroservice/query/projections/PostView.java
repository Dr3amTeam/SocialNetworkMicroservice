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

//@Data
@Entity
//@Table(name = "posts")
public class PostView {
    @Id @Column(length = 36) @Getter @Setter
    private String postId;

    @Schema(example = "Holacomo estas", description = "descripcion")
    @Column(length = 100) @Getter @Setter
    private String description;

    @Column(name = "createdDate")
    private Date createdDate;

    @Schema(example = "Holacomo estas", description = "descripcion")
    @Column(length = 100, name = "employee_id") @Getter @Setter
    private String employeeId;

    @Schema(example = "19/11/2021",description = "Fecha de creación")
    @Column(nullable = true) @Getter @Setter
    private Instant createdAt;

    @Schema(example = "21/11/2021",description = "Fecha de actualización")
    @Column(nullable = true) @Getter @Setter
    private Instant updatedAt;

    public PostView() { }

    public PostView(String postId, String description, Date createdDate, String employeeId, Instant createdAt, Instant updatedAt){
        this.postId = postId;
        this.description = description;
        this.createdDate = createdDate;
        this.employeeId = employeeId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
