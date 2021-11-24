package com.dhome.socialnetworkmicroservice.command.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostContentRepository extends JpaRepository<PostContent,String> {
    Optional<PostContent> getPostContentByPostId(String id);
}
