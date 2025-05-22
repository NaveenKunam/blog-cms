package com.example.blogcms.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private String role; // "ADMIN" or "READER"

    // Getters and setters...
}
