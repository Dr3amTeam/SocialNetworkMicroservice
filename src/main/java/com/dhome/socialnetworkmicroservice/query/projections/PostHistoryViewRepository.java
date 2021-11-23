package com.dhome.socialnetworkmicroservice.query.projections;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostHistoryViewRepository extends JpaRepository<PostHistoryView,String> {
    @Query(value = "SELECT * FROM `dhome-social`.post_history_view WHERE post_id= :postId ORDER BY created_at",nativeQuery = true)
    List<PostHistoryView> getHistoryByPostId(String postId);
    Optional<PostHistoryView> getPostHistoryViewByPostId(String postId);
}
