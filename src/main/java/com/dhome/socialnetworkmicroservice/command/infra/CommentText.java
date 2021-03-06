package com.dhome.socialnetworkmicroservice.command.infra;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class CommentText {
    @Id
    private String commentId;
    @NotNull
    private String text;
    @NotNull
    private String postId;

    public CommentText(){

    }

    public CommentText(String commentId, String text, String postId){
        this.commentId = commentId;
        this.text = text;
        this.postId = postId;
    }
}

