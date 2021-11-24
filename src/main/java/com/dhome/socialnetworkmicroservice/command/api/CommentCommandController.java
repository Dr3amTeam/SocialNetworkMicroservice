package com.dhome.socialnetworkmicroservice.command.api;

import com.dhome.common.api.ApiController;
import com.dhome.common.application.Notification;
import com.dhome.common.application.Result;
import com.dhome.socialnetworkmicroservice.command.application.dto.request.CreateCommentRequest;
import com.dhome.socialnetworkmicroservice.command.application.dto.request.EditCommentRequest;
import com.dhome.socialnetworkmicroservice.command.application.dto.response.CreateCommentResponse;
import com.dhome.socialnetworkmicroservice.command.application.dto.response.EditCommentResponse;
import com.dhome.socialnetworkmicroservice.command.application.services.CommentApplicationService;
import com.dhome.socialnetworkmicroservice.command.infra.CommentTextRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@Api(tags="Comments")
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

    @PutMapping(path="/{commentId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Actualizar comentario")
    public ResponseEntity<Object> edit(@PathVariable("commentId") String commentId, @RequestBody EditCommentRequest editCommentRequest) {
        try {
            editCommentRequest.setCommentId(commentId);
            Result<EditCommentResponse, Notification> result = commentApplicationService.edit(editCommentRequest);
            if(result.isSuccess())
                return ApiController.ok(result.getSuccess());

            return ApiController.error(result.getFailure().getErrors());
        } catch (AggregateNotFoundException exception) {
            return ApiController.notFound();
        } catch (Exception e) {
            return ApiController.serverError();
        }
    }
}
