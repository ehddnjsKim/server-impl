package com.koreait.board.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Post {
    private int id;
    private String title;
    private String content;
    private int userId;
    private LocalDateTime createdAt;
}
