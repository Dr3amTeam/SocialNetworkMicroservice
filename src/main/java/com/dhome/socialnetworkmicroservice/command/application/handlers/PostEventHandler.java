package com.dhome.socialnetworkmicroservice.command.application.handlers;

import com.dhome.socialnetworkmicroservice.command.infra.PostContent;
import com.dhome.socialnetworkmicroservice.command.infra.PostContentRepository;
import com.dhome.socialnetworkmicroservicecontracts.events.PostCreated;
import com.dhome.socialnetworkmicroservicecontracts.events.PostEdited;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@ProcessingGroup("post")
public class PostEventHandler {
    private final PostContentRepository postContentRepository;

    public PostEventHandler(PostContentRepository postContentRepository) {
        this.postContentRepository = postContentRepository;
    }
    @EventHandler
    public void on(PostCreated event){
        postContentRepository.save(new PostContent(
                event.getPostId(),
                event.getVideoUrl(),
                event.getContent(),
                event.getUploadDate(),
                event.getEmployeeId()
        ));
    }

    @EventHandler
    public void on(PostEdited event) {
        Optional<PostContent> PostContentOptional = postContentRepository.getPostContentByPostId(event.getPostId());
        PostContentOptional.ifPresent(postContentRepository::delete);
        postContentRepository.save(new PostContent(
                event.getPostId(),
                event.getVideoUrl(),
                event.getContent(),
                event.getUploadDate(),
                event.getEmployeeId()
        ));
    }

}