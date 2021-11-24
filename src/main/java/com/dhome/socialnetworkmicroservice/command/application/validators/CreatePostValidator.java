package com.dhome.socialnetworkmicroservice.command.application.validators;

import com.dhome.common.application.Notification;
import com.dhome.socialnetworkmicroservice.command.application.dto.request.CreatePostRequest;
import com.dhome.socialnetworkmicroservice.command.infra.PostContentRepository;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CreatePostValidator {
    private final PostContentRepository postContentRepository;
    public CreatePostValidator(PostContentRepository postContentRepository) {
        this.postContentRepository = postContentRepository;
    }

    public Notification validate(CreatePostRequest createPostRequest) {

        Notification notification = new Notification();

        String videoUrl = createPostRequest.getVideoUrl().trim();
        if(videoUrl.isEmpty()){
            notification.addError("Post content is required");
        }
        String content = createPostRequest.getContent().trim();
        if(content.isEmpty()){
            notification.addError("Post content is required");
        }
        Date uploadDate = createPostRequest.getUploadDate();
        if(uploadDate == null){
            notification.addError("Post upload date is required");
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
