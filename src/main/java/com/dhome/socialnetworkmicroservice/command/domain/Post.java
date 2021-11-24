package com.dhome.socialnetworkmicroservice.command.domain;

import com.dhome.socialnetworkmicroservicecontracts.commands.CreatePost;
import com.dhome.socialnetworkmicroservicecontracts.commands.EditPost;
import com.dhome.socialnetworkmicroservicecontracts.commands.FailPost;
import com.dhome.socialnetworkmicroservicecontracts.commands.PublishPost;
import com.dhome.socialnetworkmicroservicecontracts.events.PostCreated;
import com.dhome.socialnetworkmicroservicecontracts.events.PostEdited;
import com.dhome.socialnetworkmicroservicecontracts.events.PostFailed;
import com.dhome.socialnetworkmicroservicecontracts.events.PostPublished;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.Instant;
import java.util.Date;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class Post {
    @AggregateIdentifier
    private String postId;
    private String videoUrl;
    private String content;
    private Date uploadDate;
    private String employeeId;

    protected Post() {
    }

    @CommandHandler
    public Post(CreatePost command) {
        Instant now = Instant.now();
        apply(new PostCreated(
                command.getPostId(),
                command.getVideoUrl(),
                command.getContent(),
                command.getUploadDate(),
                command.getEmployeeId()
        ));
    }

    @CommandHandler
    public void handle(EditPost command) {
        Instant now = Instant.now();
        apply(
                new PostEdited(
                        command.getPostId(),
                        command.getVideoUrl(),
                        command.getContent(),
                        command.getUploadDate(),
                        command.getEmployeeId(),
                        now
                )
        );
    }

    @CommandHandler
    public void handle(PublishPost command) {
        apply(
                new PostPublished(
                        command.getPostId(),
                        command.getVideoUrl(),
                        command.getContent(),
                        command.getUploadDate(),
                        command.getEmployeeId()
                )
        );
    }

    @CommandHandler
    public void handle(FailPost command) {
        apply(
                new PostFailed(
                        command.getPostId(),
                        command.getVideoUrl(),
                        command.getContent(),
                        command.getUploadDate(),
                        command.getEmployeeId()
                )
        );
    }

    @EventSourcingHandler
    public void on(PostCreated event) {
        this.postId = event.getPostId();
        this.videoUrl = event.getVideoUrl();
        this.content = event.getContent();
        this.uploadDate = event.getUploadDate();
        this.employeeId = event.getEmployeeId();
    }

    @EventSourcingHandler
    public void on(PostEdited event) {
        this.postId = event.getPostId();
        this.videoUrl = event.getVideoUrl();
        this.content = event.getContent();
        this.uploadDate = event.getUploadDate();
        this.employeeId = event.getEmployeeId();
    }

    @EventSourcingHandler
    public void on(PostPublished event) {
        this.postId = event.getPostId();
        this.videoUrl = event.getVideoUrl();
        this.content = event.getContent();
        this.uploadDate = event.getUploadDate();
        this.employeeId = event.getEmployeeId();
    }

    @EventSourcingHandler
    public void on(PostFailed event) {
        this.postId = event.getPostId();
        this.videoUrl = event.getVideoUrl();
        this.content = event.getContent();
        this.uploadDate = event.getUploadDate();
        this.employeeId = event.getEmployeeId();
    }
}
