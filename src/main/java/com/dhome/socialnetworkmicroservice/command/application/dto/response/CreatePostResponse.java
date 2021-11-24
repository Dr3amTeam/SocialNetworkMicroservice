package com.dhome.socialnetworkmicroservice.command.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.util.Date;

@Value
public class CreatePostResponse {
    private String postId;
    private String videoUrl;
    private String content;
    private Date uploadDate;
    private String employeeId;;
}