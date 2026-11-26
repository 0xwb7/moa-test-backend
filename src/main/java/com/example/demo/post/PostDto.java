package com.example.demo.post;

import java.time.LocalDateTime;

public record PostDto(
        Long id,
        String title,
        String content,
        String author,
        LocalDateTime createdAt
) {
}
