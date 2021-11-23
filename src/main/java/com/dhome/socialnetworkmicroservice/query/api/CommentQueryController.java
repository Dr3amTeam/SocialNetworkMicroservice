package com.dhome.socialnetworkmicroservice.query.api;

import com.dhome.socialnetworkmicroservice.query.projections.*;
import io.swagger.annotations.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
@Api(tags = "Comments")
public class CommentQueryController {
    private final CommentViewRepository commentViewRepository;
    private final CommentHistoryViewRepository commentHistoryViewRepository;

    public CommentQueryController(CommentViewRepository commentViewRepository, CommentHistoryViewRepository commentHistoryViewRepository) {
        this.commentViewRepository = commentViewRepository;
        this.commentHistoryViewRepository = commentHistoryViewRepository;
    }

//    @GetMapping("")
//    @ApiOperation(value = "Get all comments", response = List.class)
//    public ResponseEntity<List<CommentView>> getAll(){
//        try{
//            return new ResponseEntity<List<CommentView>>(commentViewRepository.findAll(), HttpStatus.OK);
//        }catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
    @GetMapping("")
    @ApiOperation(value = "Get all comment", response = List.class)
    public ResponseEntity<List<CommentView>> getAll(){
        try{
            return new ResponseEntity<List<CommentView>>(commentViewRepository.findAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(path = "/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get comment by id", response = CommentView.class)
    public ResponseEntity<CommentView> getByPostId(@PathVariable("postId") String commentId) {
        try {
            Optional<CommentView> commentViewOptional = commentViewRepository.findById(commentId);
            CommentView response = commentViewOptional.get();
            return new ResponseEntity<CommentView>(response,HttpStatus.OK);
//            if (postViewOptional.isPresent()) {
//                return new ResponseEntity<PostView>(postViewOptional.get(), HttpStatus.OK);
//            }
//            return new ResponseEntity("NOT_FOUND", HttpStatus.NOT_FOUND);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{postId}")
    @ApiOperation(value = "Get by post Id", response = List.class)
    public ResponseEntity<List<CommentView>> getAllByPostId(@PathVariable("postId")String postId) {
        try {
            List<CommentView> commentView = commentViewRepository.getCommentViewByPostId(postId);
            return new ResponseEntity<List<CommentView>>(commentView, HttpStatus.OK);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
