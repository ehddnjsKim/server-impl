package com.koreait.board.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private LocalDateTime createdAt; // ✅ 카멜 케이스
}

