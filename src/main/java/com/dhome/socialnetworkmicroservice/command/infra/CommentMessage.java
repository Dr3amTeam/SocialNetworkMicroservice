package com.dhome.socialnetworkmicroservice.command.infra;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class CommentMessage {
    @Id
    private String commentId;
    @NotNull
    private String message;
    @NotNull
    private String postId;

    public CommentMessage(){

    }

    public CommentMessage(String commentId, String message, String postId){
        this.commentId = commentId;
        this.message = message;
        this.postId = postId;
    }
}
