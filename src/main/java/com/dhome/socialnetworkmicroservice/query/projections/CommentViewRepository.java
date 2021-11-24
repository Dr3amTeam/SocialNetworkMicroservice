package com.dhome.socialnetworkmicroservice.query.projections;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentViewRepository extends JpaRepository<CommentView,String> {
    Optional<CommentView> getCommentViewByCommentId(String commentId);
    List<CommentView> getCommentViewsByPostId(String postId);
}
