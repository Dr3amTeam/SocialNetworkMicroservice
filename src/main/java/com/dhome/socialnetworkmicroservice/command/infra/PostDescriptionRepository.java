package com.dhome.socialnetworkmicroservice.command.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostDescriptionRepository extends JpaRepository<PostDescription,String> {
    Optional<PostDescription> getPostDescriptionByPostId(String id);
}
