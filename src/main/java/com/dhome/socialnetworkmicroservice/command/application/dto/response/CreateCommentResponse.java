package com.dhome.socialnetworkmicroservice.command.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.util.Date;

@Value
public class CreateCommentResponse {
    private String commentId;
    private String text;
    private String commenterId;
    private String postId;
}
