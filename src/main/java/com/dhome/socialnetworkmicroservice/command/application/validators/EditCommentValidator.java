package com.dhome.socialnetworkmicroservice.command.application.validators;

import com.dhome.common.application.Notification;
import com.dhome.socialnetworkmicroservice.command.application.dto.request.EditCommentRequest;
import com.dhome.socialnetworkmicroservice.command.application.dto.request.EditPostRequest;
import com.dhome.socialnetworkmicroservice.command.domain.Comment;
import com.dhome.socialnetworkmicroservice.command.domain.Post;
import com.dhome.socialnetworkmicroservice.command.infra.CommentMessageRepository;
import com.dhome.socialnetworkmicroservice.command.infra.PostDescriptionRepository;
import org.axonframework.messaging.unitofwork.DefaultUnitOfWork;
import org.axonframework.messaging.unitofwork.UnitOfWork;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.axonframework.modelling.command.Repository;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class EditCommentValidator {
    private final CommentMessageRepository commentMessageRepository;
    private final Repository<Comment> commentRepository;

    public EditCommentValidator(CommentMessageRepository commentMessageRepository, Repository<Comment> commentRepository) {
        this.commentMessageRepository = commentMessageRepository;
        this.commentRepository = commentRepository;
    }

    public Notification validate(EditCommentRequest editCommentRequest) {

        Notification notification = new Notification();
        String commentId = editCommentRequest.getCommentId().trim();
        if(commentId.isEmpty()){
            notification.addError("Comment id is required");
        }
        loadCommentAggregate(commentId);
        String message = editCommentRequest.getMessage().trim();
        if(message.isEmpty()){
            notification.addError("Comment message is required");
        }

        String postId = editCommentRequest.getPostId().trim();
        if(postId.isEmpty()){
            notification.addError("Post id is required");
        }

        return notification;
    }

    private void loadCommentAggregate(String commentId){
        UnitOfWork unitOfWork = null;

        try{
            unitOfWork = DefaultUnitOfWork.startAndGet(null);
            commentRepository.load(commentId);
            unitOfWork.commit();
        } catch (AggregateNotFoundException ex){
            unitOfWork.commit();
            throw ex;
        } catch (Exception ex){
            if(unitOfWork != null) unitOfWork.rollback();
        }
    }
}
