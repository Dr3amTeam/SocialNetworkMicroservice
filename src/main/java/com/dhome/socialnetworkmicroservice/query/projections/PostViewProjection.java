package com.dhome.socialnetworkmicroservice.query.projections;

import com.dhome.socialnetworkmicroservicecontracts.events.PostCreated;
import com.dhome.socialnetworkmicroservicecontracts.events.PostEdited;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
@ProcessingGroup("post")
public class PostViewProjection {
    private final PostViewRepository postViewRepository;

    public PostViewProjection(PostViewRepository postViewRepository) { this.postViewRepository = postViewRepository; }

    @EventHandler
    public void on(PostCreated event, @Timestamp Instant timestamp){
        PostView postView = new PostView(event.getPostId(),
                event.getVideoUrl(),
                event.getContent(),
                event.getUploadDate(),
                event.getEmployeeId(),
                event.getOccurredOn(),
                timestamp);
        postViewRepository.save(postView);
    }

    @EventHandler
    public void on(PostEdited event, @Timestamp Instant timestamp){
        Optional<PostView> postViewOptional = postViewRepository.getPostViewByPostId(event.getPostId());
        if(postViewOptional.isPresent()){
            PostView postView = postViewOptional.get();
            postView.setVideoUrl(event.getVideoUrl());
            postView.setContent(event.getContent());
            postView.setUploadDate(event.getUploadDate());
            postView.setEmployeeId(event.getEmployeeId());
            postViewRepository.save(postView);
        }
    }
}
