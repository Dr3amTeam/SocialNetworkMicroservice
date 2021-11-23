package com.dhome.socialnetworkmicroservice.command.application.services;

import com.dhome.common.application.Notification;
import com.dhome.common.application.Result;
import com.dhome.common.application.ResultType;
import com.dhome.socialnetworkmicroservice.command.application.dto.request.CreatePostRequest;
import com.dhome.socialnetworkmicroservice.command.application.dto.response.CreatePostResponse;
import com.dhome.socialnetworkmicroservice.command.application.validators.CreatePostValidator;
import com.dhome.socialnetworkmicroservice.command.application.validators.EditPostValidator;
import com.dhome.socialnetworkmicroservice.command.infra.PostDescriptionRepository;
import com.dhome.socialnetworkmicroservicecontracts.commands.CreatePost;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
public class PostApplicationService {
    private final CreatePostValidator createPostValidator;
    private final CommandGateway commandGateway;
    private final EditPostValidator editPostValidator;
    private final PostDescriptionRepository postDescriptionRepository;

    public PostApplicationService(CreatePostValidator createPostValidator, EditPostValidator editPostValidator, CommandGateway commandGateway, PostDescriptionRepository postDescriptionRepository) {
        this.createPostValidator = createPostValidator;
        this.editPostValidator = editPostValidator;
        this.commandGateway = commandGateway;
        this.postDescriptionRepository = postDescriptionRepository;
    }

    public Result<CreatePostResponse, Notification> create(CreatePostRequest createPostRequest) throws Exception{
        Notification notification = this.createPostValidator.validate(createPostRequest);
        if (notification.hasErrors()){
            return Result.failure(notification);
        }
        String postId = UUID.randomUUID().toString();
        CreatePost createPost = new CreatePost(
                postId,
                createPostRequest.getDescription().trim(),
                createPostRequest.getCreatedDate(),
                createPostRequest.getEmployeeId()
        );
        CompletableFuture<Object> future = commandGateway.send(createPost);
        CompletableFuture<ResultType> futureResult = future.handle((ok,ex)->(ex!=null)? ResultType.FAILURE:ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE){
            throw new Exception();
        }
        CreatePostResponse createPostResponse = new CreatePostResponse(
                createPost.getPostId(),
                createPost.getDescription(),
                createPost.getCreatedDate()
        );
        return Result.success(createPostResponse);
    }


}
