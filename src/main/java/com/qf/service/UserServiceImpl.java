package com.qf.service;

import com.qf.mapper.UserMapper;
import com.qf.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public String getPasswordByUsername(String username) {
        return userMapper.getPasswordByUsername(username);
    }

    @Override
    public String getRoleByUsername(String username) {
        return userMapper.getRoleByUsername(username);
    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public int deleteUser(int uid) {
        return userMapper.deleteUser(uid);
    }

    @Override
    public User getUserByUid(int uid) {
        return userMapper.getUserByUid(uid);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public List<User> getUserList() {
        return userMapper.getUserList();
    }
}
