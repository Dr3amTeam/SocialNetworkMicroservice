package com.dhome.socialnetworkmicroservice.command.application.services;

import com.dhome.common.application.Notification;
import com.dhome.common.application.Result;
import com.dhome.common.application.ResultType;
import com.dhome.socialnetworkmicroservice.command.application.dto.request.CreateCommentRequest;
import com.dhome.socialnetworkmicroservice.command.application.dto.request.EditCommentRequest;
import com.dhome.socialnetworkmicroservice.command.application.dto.response.CreateCommentResponse;
import com.dhome.socialnetworkmicroservice.command.application.dto.response.EditCommentResponse;
import com.dhome.socialnetworkmicroservice.command.application.validators.CreateCommentValidator;
import com.dhome.socialnetworkmicroservice.command.application.validators.EditCommentValidator;
import com.dhome.socialnetworkmicroservice.command.infra.CommentTextRepository;
import com.dhome.socialnetworkmicroservicecontracts.commands.CreateComment;
import com.dhome.socialnetworkmicroservicecontracts.commands.EditComment;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
public class CommentApplicationService {
    private final CreateCommentValidator createCommentValidator;
    private final CommandGateway commandGateway;
    private final EditCommentValidator editCommentValidator;
    private final CommentTextRepository commentTextRepository;

    public CommentApplicationService(CreateCommentValidator createCommentValidator, EditCommentValidator editCommentValidator, CommandGateway commandGateway, CommentTextRepository commentTextRepository) {
        this.createCommentValidator = createCommentValidator;
        this.editCommentValidator = editCommentValidator;
        this.commandGateway = commandGateway;
        this.commentTextRepository = commentTextRepository;
    }
    public Result<CreateCommentResponse, Notification> create(CreateCommentRequest createCommentRequest) throws Exception{
        Notification notification = this.createCommentValidator.validate(createCommentRequest);
        if (notification.hasErrors()){
            return Result.failure(notification);
        }
        String commentId = UUID.randomUUID().toString();
        CreateComment createComment = new CreateComment(
                commentId,
                createCommentRequest.getText().trim(),
                createCommentRequest.getPostId().trim()
        );

        CompletableFuture<Object> future = commandGateway.send(createComment);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex)->(ex!=null)? ResultType.FAILURE:ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE){
            throw new Exception();
        }
        CreateCommentResponse createCommentResponse = new CreateCommentResponse(
                createComment.getCommentId(),
                createComment.getText(),
                createComment.getPostId()
        );
        return Result.success(createCommentResponse);
    }

    public Result<EditCommentResponse, Notification> edit (EditCommentRequest editCommentRequest) throws Exception {
        Notification notification = this.editCommentValidator.validate(editCommentRequest);
        if(notification.hasErrors()){
            return Result.failure(notification);
        }
        EditComment editComment = new EditComment(
                editCommentRequest.getCommentId().trim(),
                editCommentRequest.getText().trim(),
                editCommentRequest.getPostId().trim()
        );

        CompletableFuture<Object> future = commandGateway.send(editComment);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if(resultType == ResultType.FAILURE){
            throw new Exception();
        }
        EditCommentResponse editCommentResponse = new EditCommentResponse(
                editComment.getCommentId(),
                editComment.getText(),
                editComment.getPostId()
        );


        return Result.success(editCommentResponse);

    }
}

