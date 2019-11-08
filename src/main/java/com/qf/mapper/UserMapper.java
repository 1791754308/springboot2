package com.qf.mapper;

import com.qf.pojo.User;

import java.util.List;

public interface UserMapper {
    public String getPasswordByUsername(String username);
    public String getRoleByUsername(String username);
    public int addUser(User user);
    public int deleteUser(int uid);
    public User getUserByUid(int uid);
    public int updateUser(User user);
    public List<User> getUserList();
}
