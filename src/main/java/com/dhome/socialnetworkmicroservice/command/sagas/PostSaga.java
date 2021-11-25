package com.dhome.socialnetworkmicroservice.command.sagas;

import com.dhome.registermicroservice.contracts.commands.ValidatePost;
import com.dhome.registermicroservice.contracts.events.PostValidated;
import com.dhome.registermicroservice.contracts.events.PostValidatedFailed;
import com.dhome.socialnetworkmicroservicecontracts.commands.FailPost;
import com.dhome.socialnetworkmicroservicecontracts.commands.PublishPost;
import com.dhome.socialnetworkmicroservicecontracts.events.PostCreated;
import com.dhome.socialnetworkmicroservicecontracts.events.PostFailed;
import com.dhome.socialnetworkmicroservicecontracts.events.PostPublished;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

import javax.inject.Inject;
import java.util.Date;

@Saga
public class PostSaga {
    private String videoUrl;
    private String content;
    private Date uploadDate;
    private String employeeId;

    @Inject
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "postId")
    public void on(PostCreated event){
        this.videoUrl=event.getVideoUrl();
        this.content=event.getContent();
        this.uploadDate=event.getUploadDate();
        ValidatePost validatePost = new ValidatePost(
        event.getPostId(),
        event.getVideoUrl(),
        event.getContent(),
        event.getUploadDate(),
        event.getEmployeeId());
        commandGateway.send(validatePost);
    }
    @SagaEventHandler(associationProperty = "postId")
    @EndSaga
    public void on(PostValidatedFailed event) {
        FailPost failPost = new FailPost(
                event.getPostId(),
                event.getVideoUrl(),
                event.getContent(),
                event.getUploadDate(),
                event.getEmployeeId());
        commandGateway.send(failPost);
    }

    @SagaEventHandler(associationProperty = "postId")
    @EndSaga
    public void on(PostValidated event) {
        PublishPost publishPost = new PublishPost(
                event.getPostId(),
                event.getVideoUrl(),
                event.getContent(),
                event.getUploadDate(),
                event.getEmployeeId());
        commandGateway.send(publishPost);
    }


}
