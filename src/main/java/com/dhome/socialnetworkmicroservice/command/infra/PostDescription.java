package com.dhome.socialnetworkmicroservice.command.infra;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class PostDescription {
    @Id
    private String postId;
    @NotNull
    private String description;
    @NotNull
    private Date createdDate;

    public PostDescription(){

    }

    public PostDescription(String postId, String description, Date createdDate){
        this.postId = postId;
        this.description = description;
        this.createdDate = createdDate;
    }
}
