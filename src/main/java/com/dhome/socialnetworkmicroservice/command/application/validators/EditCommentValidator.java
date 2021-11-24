package com.dhome.socialnetworkmicroservice.command.application.validators;

import com.dhome.common.application.Notification;
import com.dhome.socialnetworkmicroservice.command.application.dto.request.EditCommentRequest;
import com.dhome.socialnetworkmicroservice.command.domain.Comment;
import com.dhome.socialnetworkmicroservice.command.infra.CommentTextRepository;
import org.axonframework.messaging.unitofwork.DefaultUnitOfWork;
import org.axonframework.messaging.unitofwork.UnitOfWork;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.axonframework.modelling.command.Repository;
import org.springframework.stereotype.Component;

@Component
public class EditCommentValidator {
    private final CommentTextRepository commentTextRepository;
    private final Repository<Comment> commentRepository;

    public EditCommentValidator(CommentTextRepository commentTextRepository, Repository<Comment> commentRepository) {
        this.commentTextRepository = commentTextRepository;
        this.commentRepository = commentRepository;
    }

    public Notification validate(EditCommentRequest editCommentRequest) {

        Notification notification = new Notification();
        String commentId = editCommentRequest.getCommentId().trim();
        if(commentId.isEmpty()){
            notification.addError("Comment id is required");
        }
        loadCommentAggregate(commentId);
        String text = editCommentRequest.getText().trim();
        if(text.isEmpty()){
            notification.addError("Comment text is required");
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
