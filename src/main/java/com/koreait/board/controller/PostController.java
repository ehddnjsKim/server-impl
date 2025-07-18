package com.koreait.board.controller;

import com.koreait.board.model.Post;
import com.koreait.board.model.User;
import com.koreait.board.service.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // ✅ 게시글 작성 (세션 인증 처리 포함)
    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody Post post, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser == null) {
            return ResponseEntity.status(401).body("로그인이 필요합니다.");
        }

        post.setUserId(loginUser.getId());
        postService.createPost(post);
        return ResponseEntity.ok("게시글 작성 완료!");
    }

    // ✅ 전체 게시글 조회 (페이징)
    @GetMapping
    public ResponseEntity<List<Post>> getPosts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        int offset = (page - 1) * size;
        List<Post> posts = postService.getPostsWithPaging(offset, size);
        return ResponseEntity.ok(posts);
    }

    // ✅ 게시글 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable int id) {
        Post post = postService.getPostById(id);
        return post != null ? ResponseEntity.ok(post) : ResponseEntity.notFound().build();
    }

    // ✅ 게시글 수정 (세션 사용자 기준으로 userId 강제 설정)
    @PutMapping("/{id}")
    public ResponseEntity<String> updatePost(@PathVariable int id, @RequestBody Post post, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser == null) {
            return ResponseEntity.status(403).body("로그인이 필요합니다.");
        }

        // 요청 body의 userId는 무시하고 세션 user로 덮어쓰기
        post.setId(id);
        post.setUserId(loginUser.getId());

        boolean result = postService.updatePost(post);
        return result ? ResponseEntity.ok("수정 완료") : ResponseEntity.badRequest().body("수정 실패");
    }

    // ✅ 게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable int id, HttpSession session) {
        Post post = postService.getPostById(id);
        User loginUser = (User) session.getAttribute("loginUser");

        if (post == null) {
            return ResponseEntity.notFound().build();
        }

        if (loginUser == null || loginUser.getId() != post.getUserId()) {
            return ResponseEntity.status(403).body("삭제 권한이 없습니다.");
        }

        boolean result = postService.deletePost(id);
        return result ? ResponseEntity.ok("삭제 완료") : ResponseEntity.badRequest().body("삭제 실패");
    }

    // ✅ 게시글 수 (페이징용)
    @GetMapping("/count")
    public ResponseEntity<Integer> getPostCount() {
        return ResponseEntity.ok(postService.getPostCount());
    }
}
