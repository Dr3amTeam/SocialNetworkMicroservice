package com.dhome.socialnetworkmicroservice.command.api;

import com.dhome.common.api.ApiController;
import com.dhome.common.application.Notification;
import com.dhome.common.application.Result;
import com.dhome.socialnetworkmicroservice.command.application.dto.request.CreatePostRequest;
import com.dhome.socialnetworkmicroservice.command.application.dto.response.CreatePostResponse;
import com.dhome.socialnetworkmicroservice.command.application.services.PostApplicationService;
import com.dhome.socialnetworkmicroservice.command.infra.PostContentRepository;
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
        try {
            Result<CreatePostResponse, Notification> result = postApplicationService.create(createPostRequest);
            if (result.isSuccess()) {
                return ApiController.created(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());

        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}