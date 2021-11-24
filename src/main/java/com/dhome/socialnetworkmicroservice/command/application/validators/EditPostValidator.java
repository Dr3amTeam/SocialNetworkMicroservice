package com.dhome.socialnetworkmicroservice.command.application.validators;

import com.dhome.common.application.Notification;
import com.dhome.socialnetworkmicroservice.command.application.dto.request.EditPostRequest;
import com.dhome.socialnetworkmicroservice.command.domain.Post;
import com.dhome.socialnetworkmicroservice.command.infra.PostContentRepository;
import org.axonframework.messaging.unitofwork.DefaultUnitOfWork;
import org.axonframework.messaging.unitofwork.UnitOfWork;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.axonframework.modelling.command.Repository;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class EditPostValidator {
    private final PostContentRepository postContentRepository;
    private final Repository<Post> postRepository;

    public EditPostValidator(PostContentRepository postContentRepository, Repository<Post> postRepository) {
        this.postContentRepository = postContentRepository;
        this.postRepository = postRepository;
    }

    public Notification validate(EditPostRequest editPostRequest) {

        Notification notification = new Notification();
        String postId = editPostRequest.getPostId().trim();
        if(postId.isEmpty()){
            notification.addError("Post id is required");
        }
        loadPostAggregate(postId);
        String videoUrl = editPostRequest.getVideoUrl().trim();
        if(videoUrl.isEmpty()){
            notification.addError("Video URL is required");
        }
        String content = editPostRequest.getContent().trim();
        if(content.isEmpty()){
            notification.addError("Post content is required");
        }
        Date uploadDate = editPostRequest.getUploadDate();
        if(uploadDate == null){
            notification.addError("Post upload date is required");
        }
        String employeeId = editPostRequest.getEmployeeId().trim();
        if(employeeId.isEmpty()){
            notification.addError("Employee ID is required");
        }
        return notification;
    }

    private void loadPostAggregate(String postId){
        UnitOfWork unitOfWork = null;

        try{
            unitOfWork = DefaultUnitOfWork.startAndGet(null);
            postRepository.load(postId);
            unitOfWork.commit();
        } catch (AggregateNotFoundException ex){
            unitOfWork.commit();
            throw ex;
        } catch (Exception ex){
            if(unitOfWork != null) unitOfWork.rollback();
        }
    }
}