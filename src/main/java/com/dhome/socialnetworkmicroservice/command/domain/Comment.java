package com.dhome.socialnetworkmicroservice.command.domain;

import com.dhome.socialnetworkmicroservicecontracts.commands.CreateComment;
import com.dhome.socialnetworkmicroservicecontracts.commands.EditComment;
import com.dhome.socialnetworkmicroservicecontracts.events.CommentCreated;
import com.dhome.socialnetworkmicroservicecontracts.events.CommentEdited;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.Instant;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class Comment {
    @AggregateIdentifier
    private String commentId;
    private String text;
    private String postId;

    protected Comment() {
    }

    @CommandHandler
    public Comment(CreateComment command) {
        Instant now = Instant.now();
        apply(new CommentCreated(
                command.getCommentId(),
                command.getText(),
                command.getPostId()
        ));
    }

    @CommandHandler
    public void handle(EditComment command) {
        Instant now = Instant.now();
        apply(
                new CommentEdited(
                        command.getCommentId(),
                        command.getText(),
                        command.getPostId(),
                        now
                )
        );
    }

    @EventSourcingHandler
    public void on(CommentCreated event) {
        this.commentId = event.getCommentId();
        this.text = event.getText();
        this.postId = event.getPostId();
    }

    @EventSourcingHandler
    public void on(CommentEdited event) {
        this.commentId = event.getCommentId();
        this.text = event.getText();
        this.postId = event.getPostId();
    }
}