package com.example.blogcms.controller;

import com.example.blogcms.dto.PostRequestDTO;
import com.example.blogcms.model.Post;
import com.example.blogcms.repository.PostRepository;
import com.example.blogcms.service.PostService;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;

    public PostController(PostService postService, PostRepository postRepository) {
        this.postService = postService;
        this.postRepository = postRepository;
    }

    @PostMapping
    public Post create(@RequestBody @Valid PostRequestDTO dto, Authentication auth) {
        return postService.createPost(dto, auth.getName());
    }

    @GetMapping
    public List<Post> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String tag,
            @RequestParam(required = false) String category
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        if (tag != null) return postRepository.findByTagsContainingIgnoreCase(tag, pageable);
        if (category != null) return postRepository.findByCategoryIgnoreCase(category, pageable);
        return postRepository.findAll(pageable).getContent();
    }
}
