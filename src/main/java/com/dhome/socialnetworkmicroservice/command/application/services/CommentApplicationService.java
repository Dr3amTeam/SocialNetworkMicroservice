package com.dhome.socialnetworkmicroservice.command.application.services;

import com.dhome.common.application.Notification;
import com.dhome.common.application.Result;
import com.dhome.common.application.ResultType;
import com.dhome.socialnetworkmicroservice.command.application.dto.request.CreateCommentRequest;
import com.dhome.socialnetworkmicroservice.command.application.dto.request.CreatePostRequest;
import com.dhome.socialnetworkmicroservice.command.application.dto.response.CreateCommentResponse;
import com.dhome.socialnetworkmicroservice.command.application.dto.response.CreatePostResponse;
import com.dhome.socialnetworkmicroservice.command.application.validators.CreateCommentValidator;
import com.dhome.socialnetworkmicroservice.command.application.validators.CreatePostValidator;
import com.dhome.socialnetworkmicroservice.command.application.validators.EditCommentValidator;
import com.dhome.socialnetworkmicroservice.command.application.validators.EditPostValidator;
import com.dhome.socialnetworkmicroservice.command.infra.CommentMessageRepository;
import com.dhome.socialnetworkmicroservice.command.infra.PostDescriptionRepository;
import com.dhome.socialnetworkmicroservicecontracts.commands.CreateComment;
import com.dhome.socialnetworkmicroservicecontracts.commands.CreatePost;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
public class CommentApplicationService {
    private final CreateCommentValidator createCommentValidator;
    private final CommandGateway commandGateway;
    private final EditCommentValidator editCommentValidator;
    private final CommentMessageRepository commentMessageRepository;

    public CommentApplicationService(CreateCommentValidator createCommentValidator, EditCommentValidator editCommentValidator, CommandGateway commandGateway, CommentMessageRepository commentMessageRepository) {
        this.createCommentValidator = createCommentValidator;
        this.editCommentValidator = editCommentValidator;
        this.commandGateway = commandGateway;
        this.commentMessageRepository = commentMessageRepository;
    }
    public Result<CreateCommentResponse, Notification> create(CreateCommentRequest createCommentRequest) throws Exception{
        Notification notification = this.createCommentValidator.validate(createCommentRequest);
        if (notification.hasErrors()){
            return Result.failure(notification);
        }
        String commentId = UUID.randomUUID().toString();
        CreateComment createComment = new CreateComment(
                commentId,
                createCommentRequest.getMessage().trim(),
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
                createComment.getMessage(),
                createComment.getPostId()
        );
        return Result.success(createCommentResponse);
    }
}
