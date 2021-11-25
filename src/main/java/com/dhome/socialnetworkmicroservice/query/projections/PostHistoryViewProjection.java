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
public class PostHistoryViewProjection {
    private final PostHistoryViewRepository postViewRepository;

    public PostHistoryViewProjection(PostHistoryViewRepository postViewRepository) { this.postViewRepository = postViewRepository; }

    @EventHandler
    public void on(PostCreated event, @Timestamp Instant timestamp){
        PostHistoryView postHistoryView = new PostHistoryView(event.getPostId(),
                event.getVideoUrl(),
                event.getContent(),
                event.getUploadDate(),
                event.getEmployeeId());
        postViewRepository.save(postHistoryView);
    }

    @EventHandler
    public void on(PostEdited event, @Timestamp Instant timestamp){
        Optional<PostHistoryView> postHistoryViewOptional = postViewRepository.getPostHistoryViewByPostId(event.getPostId());
        if(postHistoryViewOptional.isPresent()){
            PostHistoryView postHistoryView = postHistoryViewOptional.get();
            postHistoryView.setVideoUrl(event.getVideoUrl());
            postHistoryView.setContent(event.getContent());
            postHistoryView.setUploadDate(event.getUploadDate());
            postHistoryView.setEmployeeId(event.getEmployeeId());
            postViewRepository.save(postHistoryView);
        }
    }
}
