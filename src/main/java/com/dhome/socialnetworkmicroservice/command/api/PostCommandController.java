package com.dhome.socialnetworkmicroservice.command.api;

import com.dhome.common.api.ApiController;
import com.dhome.common.application.Notification;
import com.dhome.common.application.Result;
import com.dhome.common.application.ResultType;
import com.dhome.socialnetworkmicroservice.command.application.dto.request.CreatePostRequest;
import com.dhome.socialnetworkmicroservice.command.application.dto.response.CreatePostErrorResponse;
import com.dhome.socialnetworkmicroservice.command.application.dto.response.CreatePostResponse;
import com.dhome.socialnetworkmicroservice.command.application.services.PostApplicationService;
import com.dhome.socialnetworkmicroservice.command.infra.PostContentRepository;
import com.dhome.socialnetworkmicroservicecontracts.commands.CreatePost;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/posts")
@Api(tags="Posts")
public class PostCommandController {
    private final CommandGateway commandGateway;
    private final PostApplicationService postApplicationService;
    private final PostContentRepository postContentRepository;

    public PostCommandController(CommandGateway commandGateway, PostApplicationService postApplicationService, PostContentRepository postContentRepository  ){
        this.commandGateway=commandGateway;
        this.postApplicationService = postApplicationService;
        this.postContentRepository = postContentRepository;
    }

    @PostMapping(path= "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Crear publicacion")
    public ResponseEntity<Object> create(@RequestBody CreatePostRequest createPostRequest) {
        String postId = UUID.randomUUID().toString();
        CreatePost createPost = new CreatePost(
                postId,
                createPostRequest.getVideoUrl().trim(),
                createPostRequest.getContent().trim(),
                createPostRequest.getUploadDate(),
                createPostRequest.getEmployeeId()
        );
        CompletableFuture<Object> future = commandGateway.send(createPost);
        CompletableFuture<Object> futureResult = future.handle((ok, ex)-> {
            if (ex!=null) {
                return new CreatePostErrorResponse();
            }
            return new CreatePostResponse(postId);
        });
        try {
            Object result = (Object)futureResult.get();
            if (result instanceof CreatePostResponse) {
                return new ResponseEntity(result, HttpStatus.OK);
            }
            return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}