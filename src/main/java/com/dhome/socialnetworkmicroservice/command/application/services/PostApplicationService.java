package com.dhome.socialnetworkmicroservice.command.application.services;

import com.dhome.common.application.Notification;
import com.dhome.common.application.Result;
import com.dhome.common.application.ResultType;
import com.dhome.socialnetworkmicroservice.command.application.dto.request.CreatePostRequest;
import com.dhome.socialnetworkmicroservice.command.application.dto.response.CreatePostErrorResponse;
import com.dhome.socialnetworkmicroservice.command.application.dto.response.CreatePostResponse;
import com.dhome.socialnetworkmicroservice.command.application.validators.CreatePostValidator;
import com.dhome.socialnetworkmicroservice.command.application.validators.EditPostValidator;
import com.dhome.socialnetworkmicroservice.command.infra.PostContentRepository;
import com.dhome.socialnetworkmicroservicecontracts.commands.CreatePost;
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


}
