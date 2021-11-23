package com.dhome.socialnetworkmicroservice.command.application.validators;

import com.dhome.common.application.Notification;
import com.dhome.socialnetworkmicroservice.command.application.dto.request.CreatePostRequest;
import com.dhome.socialnetworkmicroservice.command.application.dto.request.EditPostRequest;
import com.dhome.socialnetworkmicroservice.command.infra.PostDescription;
import com.dhome.socialnetworkmicroservice.command.infra.PostDescriptionRepository;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class CreatePostValidator {
    private final PostDescriptionRepository postDescriptionRepository;
    public CreatePostValidator(PostDescriptionRepository postDescriptionRepository) {
        this.postDescriptionRepository = postDescriptionRepository;
    }

    public Notification validate(CreatePostRequest createPostRequest) {

        Notification notification = new Notification();
        String postId = createPostRequest.getPostId().trim();
        if(postId.isEmpty()){
            notification.addError("Post id is required");
        }

        String description = createPostRequest.getDescription().trim();
        if(description.isEmpty()){
            notification.addError("Post description is required");
        }
        Date createdDate = createPostRequest.getCreatedDate();
        if(createdDate == null){
            notification.addError("Post created date is required");
        }


        String employeeId = createPostRequest.getEmployeeId().trim();
        if(employeeId.isEmpty()){
            notification.addError("Emlpoyee ID is required");
        }

        if (notification.hasErrors()){
            return notification;
        }


        return notification;
    }
}
