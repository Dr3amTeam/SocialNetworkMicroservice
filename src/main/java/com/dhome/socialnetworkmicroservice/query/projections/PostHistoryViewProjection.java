package com.dhome.socialnetworkmicroservice.query.projections;

import com.dhome.socialnetworkmicroservicecontracts.events.PostCreated;
import com.dhome.socialnetworkmicroservicecontracts.events.PostEdited;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
@ProcessingGroup("post")
public class PostHistoryViewProjection {
    private final PostHistoryViewRepository postHistoryViewRepository;

    public PostHistoryViewProjection(PostHistoryViewRepository postHistoryViewRepository) {
        this.postHistoryViewRepository = postHistoryViewRepository;
    }

    @EventHandler
    public void on(PostCreated event, @Timestamp Instant timestamp){
        PostHistoryView postHistoryView = new PostHistoryView(event.getPostId(),
                event.getDescription(),
                event.getCreatedDate(),
                event.getEmployeeId(),
                event.getOccuredOn(),
                timestamp);
        postHistoryViewRepository.save(postHistoryView);
    }

//    @EventHandler
//    public void on(CustomerAccount event){
//        Optional<CustomerHistoryView> optionalCustomerHistoryView = accountHistoryViewRepository.getCustomerHistoryViewByAccountId(event.getCustomerId());
//        if (optionalCustomerHistoryView.isPresent()){
//            CustomerHistoryView customerHistoryView = optionalCustomerHistoryView.get();
//            customerHistoryView.setBalance(customerHistoryView.getBalance().subtract(event.getAmount()));
//            accountHistoryViewRepository.save(customerHistoryView);
//        }
//    }

    @EventHandler
    public void on(PostEdited event){
        Optional<PostHistoryView> postHistoryViewOptional = postHistoryViewRepository.getPostHistoryViewByPostId(event.getPostId());
        if (postHistoryViewOptional.isPresent()){
            PostHistoryView postHistoryView = postHistoryViewOptional.get();
            postHistoryView.setDescripcion(event.getDescription());
//            postHistoryView.setCreatedDate(event.getCreatedDate());

        }
    }
//    @EventHandler
//    public void on(FromCustomerAccount event){
//        Optional<CustomerHistoryView> optionalCustomerHistoryView = accountHistoryViewRepository.getCustomerHistoryViewByAccountId(event.getCustomerId());
//        if (optionalCustomerHistoryView.isPresent()){
//            CustomerHistoryView customerHistoryView = optionalCustomerHistoryView.get();
//            customerHistoryView.setBalance(customerHistoryView.getBalance().subtract(event.getAmount()));
//            accountHistoryViewRepository.save(customerHistoryView);
//        }
//    }
}
