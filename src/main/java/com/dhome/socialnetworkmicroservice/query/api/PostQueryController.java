package com.dhome.socialnetworkmicroservice.query.api;

import com.dhome.socialnetworkmicroservice.config.SwaggerConfig;
import com.dhome.socialnetworkmicroservice.query.projections.*;
import io.swagger.annotations.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
@Api(tags = {SwaggerConfig.POSTS})
public class PostQueryController {
    private final PostViewRepository postViewRepository;
    private final PostHistoryViewRepository postHistoryViewRepository;

    public PostQueryController(PostViewRepository postViewRepository, PostHistoryViewRepository postHistoryViewRepository) {
        this.postViewRepository = postViewRepository;
        this.postHistoryViewRepository = postHistoryViewRepository;
    }

    @GetMapping("")
    @ApiOperation(value = "Obtener todos los posts", response = List.class)
    public ResponseEntity<List<PostView>> getAll() {
        try {
            return new ResponseEntity<List<PostView>>(postViewRepository.findAll(), HttpStatus.OK);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/employeeId/{employeeId}")
    @ApiOperation(value = "Obtener todos los posts por ID de empleador", response = List.class)
    public ResponseEntity<List<PostView>> getAllByEmployeeId(@PathVariable("employeeId")String employeeId) {
        try {
            List<PostView> postViews = postViewRepository.getPostViewsByEmployeeId(employeeId);
            return new ResponseEntity<List<PostView>>(postViews, HttpStatus.OK);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtener post por Id", response = List.class)
    public ResponseEntity<PostView> getAll(@PathVariable("d") String id) {
        try {
            Optional<PostView> postView = postViewRepository.findById(id);
            PostView response = postView.get();
            return new ResponseEntity<PostView>(response,HttpStatus.OK);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/history/{id}")
    @ApiOperation(value = "Obtener historial por Id de post", response = List.class)
    public ResponseEntity<List<PostHistoryView>> getHistoryByPostId(@PathVariable("id") String id) {
        try {
            List<PostHistoryView> posts = postHistoryViewRepository.getHistoryByPostId(id);
            return new ResponseEntity<List<PostHistoryView>>(posts, HttpStatus.OK);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
