package com.dhome.socialnetworkmicroservice.command.application.validators;

import com.dhome.common.application.Notification;
import com.dhome.socialnetworkmicroservice.command.application.dto.request.CreateCommentRequest;
import com.dhome.socialnetworkmicroservice.command.application.dto.request.CreatePostRequest;
import com.dhome.socialnetworkmicroservice.command.infra.CommentMessageRepository;
import com.dhome.socialnetworkmicroservice.command.infra.PostDescriptionRepository;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CreateCommentValidator {
    private final CommentMessageRepository commentMessageRepository;
    public CreateCommentValidator(CommentMessageRepository commentMessageRepository) {
        this.commentMessageRepository = commentMessageRepository;
    }

    public Notification validate(CreateCommentRequest createCommentRequest) {

        Notification notification = new Notification();
        String commentId = createCommentRequest.getCommentId().trim();
        if(commentId.isEmpty()){
            notification.addError("Comment id is required");
        }

        String message = createCommentRequest.getMessage().trim();
        if(message.isEmpty()){
            notification.addError("Comment message is required");
        }

        String postId = createCommentRequest.getPostId().trim();
        if(postId.isEmpty()){
            notification.addError("Post id is required");
        }



        if (notification.hasErrors()){
            return notification;
        }


        return notification;
    }
}
