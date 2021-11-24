package com.dhome.socialnetworkmicroservice.command.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.util.Date;


public class CreatePostResponse {
    private String postId;

    public CreatePostResponse(String postId){
        this.postId=postId;
    }

    public String getPostId() {
        return postId;
    }


}