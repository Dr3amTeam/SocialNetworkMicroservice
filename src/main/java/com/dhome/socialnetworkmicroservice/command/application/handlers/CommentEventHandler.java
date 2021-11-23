package com.dhome.socialnetworkmicroservice.command.application.handlers;

import com.dhome.socialnetworkmicroservice.command.infra.CommentMessage;
import com.dhome.socialnetworkmicroservice.command.infra.CommentMessageRepository;
import com.dhome.socialnetworkmicroservice.command.infra.PostDescription;
import com.dhome.socialnetworkmicroservice.command.infra.PostDescriptionRepository;
import com.dhome.socialnetworkmicroservicecontracts.events.CommentCreated;
import com.dhome.socialnetworkmicroservicecontracts.events.CommentEdited;
import com.dhome.socialnetworkmicroservicecontracts.events.PostCreated;
import com.dhome.socialnetworkmicroservicecontracts.events.PostEdited;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@ProcessingGroup("comment")
public class CommentEventHandler{

    private final CommentMessageRepository commentMessageRepository;

    public CommentEventHandler(CommentMessageRepository commentMessageRepository) {
        this.commentMessageRepository = commentMessageRepository;
    }
    @EventHandler
    public void on(CommentCreated event){
        commentMessageRepository.save(new CommentMessage(
                event.getCommentId(),
                event.getMessage(),
                event.getPostId()
        ));
    }

    @EventHandler
    public void on(CommentEdited event) {
        Optional<CommentMessage> commentMessageOptional = commentMessageRepository.getCommentMessageByCommentId(event.getCommentId());
        commentMessageOptional.ifPresent(commentMessageRepository::delete);
        commentMessageRepository.save(new CommentMessage(
                event.getCommentId(),
                event.getMessage(),
                event.getPostId()
        ));
    }

}
