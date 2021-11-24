package com.dhome.socialnetworkmicroservice.query.projections;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentHistoryViewRepository extends JpaRepository<CommentHistoryView,String> {
    @Query(value = "SELECT * FROM comment_history_view WHERE comment_id= :commentId ORDER BY created_at",nativeQuery = true)
    List<CommentHistoryView> getHistoryByCommentId(String commentId);

    Optional<CommentHistoryView> getCommentHistoryViewByCommentId(String commentId);
}
