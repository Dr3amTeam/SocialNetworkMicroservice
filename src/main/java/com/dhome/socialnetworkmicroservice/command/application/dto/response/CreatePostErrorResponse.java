package com.dhome.socialnetworkmicroservice.command.application.dto.response;



public class CreatePostErrorResponse {
    private String message;

    public CreatePostErrorResponse() {
        this.message="Error creating this post";
    }

    public CreatePostErrorResponse(String message) {
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

}
