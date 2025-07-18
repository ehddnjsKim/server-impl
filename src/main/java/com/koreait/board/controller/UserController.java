package com.koreait.board.controller;

import com.koreait.board.model.User;
import com.koreait.board.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        userService.registerUser(user);
        return ResponseEntity.ok("회원가입 성공!");
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginInfo, HttpSession session) {
        String username = loginInfo.get("username");
        String password = loginInfo.get("password");

        User user = userService.login(username, password);

        if (user != null) {
            // 세션에 로그인 사용자 저장
            session.setAttribute("loginUser", user);

            // 프론트에서 사용할 수 있도록 사용자 ID와 username 반환
            Map<String, Object> response = new HashMap<>();
            response.put("id", user.getId());
            response.put("username", user.getUsername());

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body("아이디 또는 비밀번호가 일치하지 않습니다.");
        }
    }

    // 로그인된 사용자 정보 조회
    @GetMapping("/session")
    public ResponseEntity<User> getSessionUser(HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser != null) {
            return ResponseEntity.ok(loginUser);
        } else {
            return ResponseEntity.status(401).build(); // 로그인하지 않은 상태
        }
    }

    // 회원 정보 조회
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 회원 정보 수정
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable int id, @RequestBody User user) {
        user.setId(id);
        int result = userService.updateUser(user);
        if (result > 0) {
            return ResponseEntity.ok("회원 정보가 수정되었습니다.");
        } else {
            return ResponseEntity.badRequest().body("회원 정보 수정 실패");
        }
    }
}
