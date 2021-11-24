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
public class CommentHistoryViewProjection {
    private final CommentHistoryViewRepository commentViewRepository;

    public CommentHistoryViewProjection(CommentHistoryViewRepository commentViewRepository) { this.commentViewRepository = commentViewRepository; }

    @EventHandler
    public void on(CommentCreated event, @Timestamp Instant timestamp){
        CommentHistoryView commentHistoryView = new CommentHistoryView(event.getCommentId(),
                event.getText(),
                event.getCommenterId(),
                event.getPostId(),
                event.getOccurredOn(),
                timestamp);
        commentViewRepository.save(commentHistoryView);
    }

    @EventHandler
    public void on(CommentEdited event, @Timestamp Instant timestamp){
        Optional<CommentHistoryView> commentHistoryViewOptional = commentViewRepository.getCommentHistoryViewByCommentId(event.getCommentId());
        if(commentHistoryViewOptional.isPresent()){
            CommentHistoryView commentHistoryView = commentHistoryViewOptional.get();
            commentHistoryView.setText(event.getText());
            commentHistoryView.setCommenterId(event.getCommenterId());
            commentHistoryView.setPostId(event.getPostId());
            commentViewRepository.save(commentHistoryView);
        }
    }
}
