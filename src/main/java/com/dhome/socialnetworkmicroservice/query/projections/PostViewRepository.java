package com.dhome.socialnetworkmicroservice.query.projections;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostViewRepository extends JpaRepository<PostView, String> {
    Optional<PostView> getPostViewByPostId(String postId);
//    List<PostView> getPostByEmployeeId(String employeeId);


}