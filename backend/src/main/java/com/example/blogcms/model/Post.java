package com.example.blogcms.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document
public class Post {
    @Id
    private String id;
    private String title;
    private String content;
    private String author;
    private String category;
    private List<String> tags;
    private String imageUrl;
    private LocalDateTime createdAt;

    // Getters and setters...
}
