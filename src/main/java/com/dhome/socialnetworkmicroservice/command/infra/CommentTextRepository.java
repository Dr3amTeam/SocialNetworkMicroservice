package com.dhome.socialnetworkmicroservice.command.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentTextRepository extends JpaRepository<CommentText,String> {
    Optional<CommentText> getCommentTextByCommentId(String id);
}

