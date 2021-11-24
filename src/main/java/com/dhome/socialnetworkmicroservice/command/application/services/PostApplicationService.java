package com.dhome.socialnetworkmicroservice.command.application.services;

import com.dhome.common.application.Notification;
import com.dhome.common.application.Result;
import com.dhome.common.application.ResultType;
import com.dhome.socialnetworkmicroservice.command.application.dto.request.CreateCommentRequest;
import com.dhome.socialnetworkmicroservice.command.application.dto.request.CreatePostRequest;
import com.dhome.socialnetworkmicroservice.command.application.dto.request.EditCommentRequest;
import com.dhome.socialnetworkmicroservice.command.application.dto.request.EditPostRequest;
import com.dhome.socialnetworkmicroservice.command.application.dto.response.*;
import com.dhome.socialnetworkmicroservice.command.application.validators.CreatePostValidator;
import com.dhome.socialnetworkmicroservice.command.application.validators.EditPostValidator;
import com.dhome.socialnetworkmicroservice.command.infra.PostContentRepository;
import com.dhome.socialnetworkmicroservicecontracts.commands.CreateComment;
import com.dhome.socialnetworkmicroservicecontracts.commands.CreatePost;
import com.dhome.socialnetworkmicroservicecontracts.commands.EditComment;
import com.dhome.socialnetworkmicroservicecontracts.commands.EditPost;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
public class PostApplicationService {
    private final CreatePostValidator createPostValidator;
    private final CommandGateway commandGateway;
    private final EditPostValidator editPostValidator;
    private final PostContentRepository postContentRepository;

    public PostApplicationService(CreatePostValidator createPostValidator, EditPostValidator editPostValidator, CommandGateway commandGateway, PostContentRepository postContentRepository) {
        this.createPostValidator = createPostValidator;
        this.editPostValidator = editPostValidator;
        this.commandGateway = commandGateway;
        this.postContentRepository = postContentRepository;
    }

    public Result<CreatePostResponse, Notification> create(CreatePostRequest createPostRequest) throws Exception{
        Notification notification = this.createPostValidator.validate(createPostRequest);
        if (notification.hasErrors()){
            return Result.failure(notification);
        }
        String commentId = UUID.randomUUID().toString();
        CreatePost createPost = new CreatePost(
                commentId,
                createPostRequest.getVideoUrl().trim(),
                createPostRequest.getContent().trim(),
                createPostRequest.getUploadDate(),
                createPostRequest.getEmployeeId().trim()
        );

        CompletableFuture<Object> future = commandGateway.send(createPost);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex)->(ex!=null)? ResultType.FAILURE:ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE){
            throw new Exception();
        }
        CreatePostResponse createPostResponse = new CreatePostResponse(
                createPost.getPostId()
        );
        return Result.success(createPostResponse);
    }

    public Result<EditPostResponse, Notification> edit (EditPostRequest editPostRequest) throws Exception {
        Notification notification = this.editPostValidator.validate(editPostRequest);
        if(notification.hasErrors()){
            return Result.failure(notification);
        }
        EditPost editPost = new EditPost(
                editPostRequest.getPostId().trim(),
                editPostRequest.getVideoUrl().trim(),
                editPostRequest.getContent().trim(),
                editPostRequest.getUploadDate(),
                editPostRequest.getEmployeeId().trim()
        );

        CompletableFuture<Object> future = commandGateway.send(editPost);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if(resultType == ResultType.FAILURE){
            throw new Exception();
        }

        EditPostResponse editPostResponse = new EditPostResponse(
                editPost.getPostId(),
                editPost.getVideoUrl(),
                editPost.getContent(),
                editPost.getUploadDate(),
                editPost.getEmployeeId()
        );


        return Result.success(editPostResponse);

    }


}
