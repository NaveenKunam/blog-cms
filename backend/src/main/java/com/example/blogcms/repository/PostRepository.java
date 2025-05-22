package com.example.blogcms.repository;

import com.example.blogcms.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {
    List<Post> findByTagsContainingIgnoreCase(String tag, Pageable pageable);
    List<Post> findByCategoryIgnoreCase(String category, Pageable pageable);
}
