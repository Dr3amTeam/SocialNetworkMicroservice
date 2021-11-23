package com.dhome.socialnetworkmicroservice.query.api;

import com.dhome.socialnetworkmicroservice.query.projections.*;
import io.swagger.annotations.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
@Api(tags = "Posts")
public class PostQueryController {
    private final PostViewRepository postViewRepository;
    private final PostHistoryViewRepository postHistoryViewRepository;

    public PostQueryController(PostViewRepository postViewRepository, PostHistoryViewRepository postHistoryViewRepository) {
        this.postViewRepository=postViewRepository;
        this.postHistoryViewRepository = postHistoryViewRepository;
    }

    @GetMapping("")
    @ApiOperation(value = "Get all posts", response = List.class)
    public ResponseEntity<List<PostView>> getAll(){
        try{
            return new ResponseEntity<List<PostView>>(postViewRepository.findAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get post by id", response = PostView.class)
    public ResponseEntity<PostView> getByPostId(@PathVariable("postId") String postId) {
        try {
            Optional<PostView> postViewOptional = postViewRepository.findById(postId);
            PostView response = postViewOptional.get();
            return new ResponseEntity<PostView>(response,HttpStatus.OK);
//            if (postViewOptional.isPresent()) {
//                return new ResponseEntity<PostView>(postViewOptional.get(), HttpStatus.OK);
//            }
//            return new ResponseEntity("NOT_FOUND", HttpStatus.NOT_FOUND);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/history/{id}")
    @ApiOperation(value = "Obtener historial por Id de la publicacion", response = List.class)
    public ResponseEntity<List<PostHistoryView>> getHistoryById(@PathVariable("id") String id) {
        try {
            List<PostHistoryView> posts = postHistoryViewRepository.getHistoryByPostId(id);
            return new ResponseEntity<List<PostHistoryView>>(posts, HttpStatus.OK);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
