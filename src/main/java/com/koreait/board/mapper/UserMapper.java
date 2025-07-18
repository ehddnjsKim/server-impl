package com.koreait.board.mapper;

import com.koreait.board.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface UserMapper {
    void insertUser(User user);

    User findByUsernameAndPassword(Map<String, Object> param);

    User findById(int id);         // 회원 조회

    int updateUser(User user);     // 회원 수정
}
