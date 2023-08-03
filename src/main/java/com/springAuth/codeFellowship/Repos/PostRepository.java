package com.springAuth.codeFellowship.Repos;

import com.springAuth.codeFellowship.Models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // You can define additional query methods here if needed
    Post getPostById(Long id);
}