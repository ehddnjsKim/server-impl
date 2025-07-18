package com.koreait.board.service;

import com.koreait.board.mapper.PostMapper;
import com.koreait.board.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostMapper postMapper;

    @Autowired
    public PostService(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    public void createPost(Post post) {
        postMapper.insertPost(post);
    }

    // 페이징 적용된 게시글 목록 조회
    public List<Post> getPostsWithPaging(int offset, int size) {
        return postMapper.getPostsWithPaging(offset, size);
    }

    // 전체 게시글 수 반환 (선택사항: 프론트에서 totalPages 계산할 때 유용)
    public int getPostCount() {
        return postMapper.getPostCount();
    }

    public Post getPostById(int id) {
        return postMapper.getPostById(id);
    }

    public boolean updatePost(Post post) {
        return postMapper.updatePost(post) > 0;
    }

    public boolean deletePost(int id) {
        return postMapper.deletePost(id) > 0;
    }
}
