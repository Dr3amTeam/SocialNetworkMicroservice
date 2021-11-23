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
    private final CommentHistoryViewRepository commentHistoryViewRepository;

    public CommentHistoryViewProjection(CommentHistoryViewRepository commentHistoryViewRepository) {
        this.commentHistoryViewRepository = commentHistoryViewRepository;
    }

    @EventHandler
    public void on(CommentCreated event, @Timestamp Instant timestamp){
        CommentHistoryView commentHistoryView = new CommentHistoryView(event.getCommentId(),
                event.getMessage(),
                event.getPostId(),
                event.getOccuredOn(),
                timestamp);
        commentHistoryViewRepository.save(commentHistoryView);
    }



    @EventHandler
    public void on(CommentEdited event){
        Optional<CommentHistoryView> commentHistoryViewOptional = commentHistoryViewRepository.getCommentHistoryViewByCommentHistoryId(event.getCommentId());
        if (commentHistoryViewOptional.isPresent()){
            CommentHistoryView commentHistoryView = commentHistoryViewOptional.get();
            commentHistoryView.setMessage(event.getMessage());

        }
    }
}
