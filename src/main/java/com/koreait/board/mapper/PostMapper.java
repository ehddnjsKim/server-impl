package com.koreait.board.mapper;

import com.koreait.board.model.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {

    // 기본 CRUD
    void insertPost(Post post);
    List<Post> getPosts(); // 전체 조회
    Post getPostById(int id);
    int updatePost(Post post);
    int deletePost(int id);

    // 페이징 기능 추가
    List<Post> getPostsWithPaging(@Param("offset") int offset, @Param("size") int size);
    int getPostCount();
}
