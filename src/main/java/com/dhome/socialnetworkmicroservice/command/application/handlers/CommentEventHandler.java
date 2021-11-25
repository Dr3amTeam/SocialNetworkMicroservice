package com.dhome.socialnetworkmicroservice.command.application.handlers;

import com.dhome.socialnetworkmicroservice.command.infra.CommentText;
import com.dhome.socialnetworkmicroservice.command.infra.CommentTextRepository;
import com.dhome.socialnetworkmicroservicecontracts.events.CommentCreated;
import com.dhome.socialnetworkmicroservicecontracts.events.CommentEdited;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@ProcessingGroup("comment")
public class CommentEventHandler {
    private final CommentTextRepository commentTextRepository;

    public CommentEventHandler(CommentTextRepository commentTextRepository) {
        this.commentTextRepository = commentTextRepository;
    }
    @EventHandler
    public void on(CommentCreated event){
        commentTextRepository.save(new CommentText(
                event.getCommentId(),
                event.getText(),
                event.getPostId()
        ));
    }

    @EventHandler
    public void on(CommentEdited event) {
        Optional<CommentText> commentTextOptional = commentTextRepository.getCommentTextByCommentId(event.getCommentId());
        commentTextOptional.ifPresent(commentTextRepository::delete);
        commentTextRepository.save(new CommentText(
                event.getCommentId(),
                event.getText(),
                event.getPostId()
        ));
    }
}