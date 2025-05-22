package com.example.blogcms.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class PostRequestDTO {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    private String category;
    private List<String> tags;

    // Getters and setters...
}
