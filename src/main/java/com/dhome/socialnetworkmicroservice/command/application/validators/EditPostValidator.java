package com.dhome.socialnetworkmicroservice.command.application.validators;

import com.dhome.common.application.Notification;
import com.dhome.socialnetworkmicroservice.command.application.dto.request.EditPostRequest;
import com.dhome.socialnetworkmicroservice.command.domain.Post;
import com.dhome.socialnetworkmicroservice.command.infra.PostDescriptionRepository;
import org.axonframework.messaging.unitofwork.DefaultUnitOfWork;
import org.axonframework.messaging.unitofwork.UnitOfWork;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.axonframework.modelling.command.Repository;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class EditPostValidator {
    private final PostDescriptionRepository postDescriptionRepository;
    private final Repository<Post> postRepository;

    public EditPostValidator(PostDescriptionRepository postDescriptionRepository, Repository<Post> postRepository) {
        this.postDescriptionRepository = postDescriptionRepository;
        this.postRepository = postRepository;
    }

    public Notification validate(EditPostRequest editPostRequest) {

        Notification notification = new Notification();
        String postId = editPostRequest.getPostId().trim();
        if(postId.isEmpty()){
            notification.addError("Post id is required");
        }
        loadPostAggregate(postId);
        String description = editPostRequest.getDescription().trim();
        if(description.isEmpty()){
            notification.addError("Post description is required");
        }
        Date createdDate = editPostRequest.getCreatedDate();
        if(createdDate == null){
            notification.addError("Post created date is required");
        }


        String employeeId = editPostRequest.getEmployeeId().trim();
        if(employeeId.isEmpty()){
            notification.addError("Emlpoyee ID is required");
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
