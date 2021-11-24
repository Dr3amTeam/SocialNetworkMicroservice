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

    public CommentViewProjection(CommentViewRepository commentViewRepository) { this.commentViewRepository=commentViewRepository; }

    @EventHandler
    public void on(CommentCreated event, @Timestamp Instant timestamp){
        CommentView commentView = new CommentView(event.getCommentId(),
                event.getText(),
                event.getCommenterId(),
                event.getPostId(),
                event.getOccurredOn(),
                timestamp);
        commentViewRepository.save(commentView);
    }

    @EventHandler
    public void on(CommentEdited event, @Timestamp Instant timestamp){
        Optional<CommentView> commentViewOptional = commentViewRepository.getCommentViewByCommentId(event.getCommentId());
        if(commentViewOptional.isPresent()){
            CommentView commentView = commentViewOptional.get();
            commentView.setText(event.getText());
            commentView.setCommenterId(event.getCommenterId());
            commentView.setPostId(event.getPostId());
            commentViewRepository.save(commentView);
        }
    }
}
