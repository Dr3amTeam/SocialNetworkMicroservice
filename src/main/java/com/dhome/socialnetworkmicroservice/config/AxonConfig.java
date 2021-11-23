package com.dhome.socialnetworkmicroservice.config;

import com.dhome.socialnetworkmicroservice.command.domain.Comment;
import com.dhome.socialnetworkmicroservice.command.domain.Post;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.modelling.command.Repository;
import org.axonframework.spring.config.AxonConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

//    @Autowired
//    private AxonConfiguration axonConfiguration;
//    @Autowired
//    private EventBus eventBus;
//
//    @Autowired
//    public void configure(@Qualifier("localSegment") SimpleCommandBus simpleCommandBus){
//        simpleCommandBus.registerDispatchInterceptor(new BeanValidationInterceptor<>());
//    }
    @Bean
    public Repository<Post> eventSourcingRepository(EventStore eventStore){
        return EventSourcingRepository.builder(Post.class)
                .eventStore(eventStore)
                .build();
    }

    @Bean
    public Repository<Comment> eventSourcingRepository2(EventStore eventStore){
        return EventSourcingRepository.builder(Comment.class)
                .eventStore(eventStore)
                .build();
    }
}
