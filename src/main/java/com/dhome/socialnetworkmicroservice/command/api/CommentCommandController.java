package com.dhome.socialnetworkmicroservice.command.api;

import com.dhome.common.api.ApiController;
import com.dhome.common.application.Notification;
import com.dhome.common.application.Result;
import com.dhome.socialnetworkmicroservice.command.application.dto.request.CreateCommentRequest;
import com.dhome.socialnetworkmicroservice.command.application.dto.response.CreateCommentResponse;
import com.dhome.socialnetworkmicroservice.command.application.services.CommentApplicationService;
import com.dhome.socialnetworkmicroservice.command.infra.CommentTextRepository;
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
@RequestMapping("/comments")
@Api(tags="Comment")
public class CommentCommandController {
    private final CommandGateway commandGateway;
    private final CommentApplicationService commentApplicationService;
    private final CommentTextRepository commentTextRepository;

    public CommentCommandController(CommandGateway commandGateway, CommentApplicationService commentApplicationService, CommentTextRepository commentTextRepository  ){
        this.commandGateway=commandGateway;
        this.commentApplicationService = commentApplicationService;
        this.commentTextRepository = commentTextRepository;
    }

    @PostMapping(path= "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Crear comentarios")
    public ResponseEntity<Object> create(@RequestBody CreateCommentRequest createCommentRequest) {
        try {
            Result<CreateCommentResponse, Notification> result = commentApplicationService.create(createCommentRequest);
            if (result.isSuccess()) {
                return ApiController.created(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());

        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
