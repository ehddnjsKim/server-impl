package com.koreait.board.service;

import com.koreait.board.mapper.UserMapper;
import com.koreait.board.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    // 회원가입
    public void registerUser(User user) {
        userMapper.insertUser(user);
    }

    // 로그인
    public User login(String username, String password) {
        Map<String, Object> param = new HashMap<>();
        param.put("username", username);
        param.put("password", password);
        return userMapper.findByUsernameAndPassword(param);
    }

    // 회원 정보 조회
    public User getUserById(int id) {
        return userMapper.findById(id);
    }

    // 회원 정보 수정
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }
}
