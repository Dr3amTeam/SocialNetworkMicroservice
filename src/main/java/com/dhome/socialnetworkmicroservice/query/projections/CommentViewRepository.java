package com.dhome.socialnetworkmicroservice.query.projections;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentViewRepository extends JpaRepository<CommentView, String> {
    Optional<CommentView> getCommentViewByCommentId(String commentId);
    List<CommentView> getCommentViewByPostId(String postId);
}