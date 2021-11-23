package com.dhome.socialnetworkmicroservice.command.application.handlers;

import com.dhome.socialnetworkmicroservice.command.infra.PostDescription;
import com.dhome.socialnetworkmicroservice.command.infra.PostDescriptionRepository;
import com.dhome.socialnetworkmicroservicecontracts.events.PostCreated;
import com.dhome.socialnetworkmicroservicecontracts.events.PostEdited;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@ProcessingGroup("post")
public class PostEventHandler{
    private final PostDescriptionRepository postDescriptionRepository;

    public PostEventHandler(PostDescriptionRepository postDescriptionRepository) {
        this.postDescriptionRepository = postDescriptionRepository;
    }
    @EventHandler
    public void on(PostCreated event){
        postDescriptionRepository.save(new PostDescription(
                event.getPostId(),
                event.getDescription(),
                event.getCreatedDate()
                ));
    }

    @EventHandler
    public void on(PostEdited event) {
        Optional<PostDescription> PostDescriptionOptional = postDescriptionRepository.getPostDescriptionByPostId(event.getPostId());
        PostDescriptionOptional.ifPresent(postDescriptionRepository::delete);
        postDescriptionRepository.save(new PostDescription(
                event.getPostId(),
                event.getDescription(),
                event.getCreatedDate()
        ));
    }

}
