package com.dhome.socialnetworkmicroservice.query.projections;


import com.dhome.socialnetworkmicroservicecontracts.events.CommentCreated;
import com.dhome.socialnetworkmicroservicecontracts.events.CommentEdited;
import com.dhome.socialnetworkmicroservicecontracts.events.PostCreated;
import com.dhome.socialnetworkmicroservicecontracts.events.PostEdited;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
@ProcessingGroup("comment")
public class CommentViewProjection {
    private final CommentViewRepository commentViewRepository;

    public CommentViewProjection(CommentViewRepository commentViewRepository) {
        this.commentViewRepository = commentViewRepository;
    }

    @EventHandler
    public void on(CommentCreated event, @Timestamp Instant timestamp){
        CommentView commentView = new CommentView(event.getCommentId(),
                event.getMessage(),
                event.getPostId(),
                event.getOccuredOn(),
                timestamp);
        commentViewRepository.save(commentView);
    }



    @EventHandler
    public void on(CommentEdited event){
        Optional<CommentView> commentViewOptional = commentViewRepository.getCommentViewByCommentId(event.getCommentId());
        if (commentViewOptional.isPresent()){
            CommentView commentView = commentViewOptional.get();
            commentView.setMessage(event.getMessage());
            commentViewRepository.save(commentView);
        }
    }
}
