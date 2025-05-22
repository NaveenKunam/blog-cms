package com.example.blogcms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/upload")
public class UploadController {

    private static final String UPLOAD_DIR = "uploads";

    @PostMapping
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws IOException {
        String filename = UUID.randomUUID() + "_" + StringUtils.cleanPath(file.getOriginalFilename());
        File dir = new File(UPLOAD_DIR);
        if (!dir.exists()) dir.mkdirs();
        file.transferTo(new File(dir, filename));
        return ResponseEntity.ok("/uploads/" + filename);
    }
}
