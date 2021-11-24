package com.dhome.socialnetworkmicroservice.query.api;

import com.dhome.socialnetworkmicroservice.config.SwaggerConfig;
import com.dhome.socialnetworkmicroservice.query.projections.*;
import io.swagger.annotations.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
@Api(tags = {SwaggerConfig.COMMENTS})
public class CommentQueryController {
    private final CommentViewRepository commentViewRepository;
    private final CommentHistoryViewRepository commentHistoryViewRepository;

    public CommentQueryController(CommentViewRepository commentViewRepository, CommentHistoryViewRepository commentHistoryViewRepository) {
        this.commentViewRepository = commentViewRepository;
        this.commentHistoryViewRepository = commentHistoryViewRepository;
    }

    @GetMapping("")
    @ApiOperation(value = "Obtener todos los comentarios", response = List.class)
    public ResponseEntity<List<CommentView>> getAll() {
        try {
            return new ResponseEntity<List<CommentView>>(commentViewRepository.findAll(), HttpStatus.OK);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/postId/{postId}")
    @ApiOperation(value = "Obtener todos los comentarios por Id de post", response = List.class)
    public ResponseEntity<List<CommentView>> getAllByPostId(@PathVariable("postId")String postId) {
        try {
            List<CommentView> commentViews = commentViewRepository.getCommentViewsByPostId(postId);
            return new ResponseEntity<List<CommentView>>(commentViews, HttpStatus.OK);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtener comentario por Id", response = List.class)
    public ResponseEntity<CommentView> getAll(@PathVariable("id") String id) {
        try {
            Optional<CommentView> commentView = commentViewRepository.findById(id);
            CommentView response = commentView.get();
            return new ResponseEntity<CommentView>(response,HttpStatus.OK);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/history/{id}")
    @ApiOperation(value = "Obtener historial por Id de comentario", response = List.class)
    public ResponseEntity<List<CommentHistoryView>> getHistoryByCommentId(@PathVariable("id") String id) {
        try {
            List<CommentHistoryView> comments = commentHistoryViewRepository.getHistoryByCommentId(id);
            return new ResponseEntity<List<CommentHistoryView>>(comments, HttpStatus.OK);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
