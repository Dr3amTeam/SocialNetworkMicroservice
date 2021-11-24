package com.dhome.socialnetworkmicroservice.command.application.validators;

import com.dhome.common.application.Notification;
import com.dhome.socialnetworkmicroservice.command.application.dto.request.CreateCommentRequest;
import com.dhome.socialnetworkmicroservice.command.infra.CommentTextRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateCommentValidator {
    private final CommentTextRepository commentTextRepository;
    public CreateCommentValidator(CommentTextRepository commentTextRepository) {
        this.commentTextRepository = commentTextRepository;
    }

    public Notification validate(CreateCommentRequest createCommentRequest) {

        Notification notification = new Notification();

        String text = createCommentRequest.getText().trim();
        if(text.isEmpty()){
            notification.addError("Comment text is required");
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
