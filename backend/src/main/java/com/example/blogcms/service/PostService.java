package com.example.blogcms.service;

import com.example.blogcms.dto.PostRequestDTO;
import com.example.blogcms.model.Post;
import com.example.blogcms.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PostService {

    private final PostRepository repo;

    public PostService(PostRepository repo) {
        this.repo = repo;
    }

    public Post createPost(PostRequestDTO dto, String author) {
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setCategory(dto.getCategory());
        post.setTags(dto.getTags());
        post.setAuthor(author);
        post.setCreatedAt(LocalDateTime.now());
        return repo.save(post);
    }
}
