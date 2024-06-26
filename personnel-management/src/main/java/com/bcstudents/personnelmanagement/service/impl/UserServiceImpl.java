package com.bcstudents.personnelmanagement.service.impl;

import com.bcstudents.personnelmanagement.bean.User;
import com.bcstudents.personnelmanagement.mapper.UserMapper;
import com.bcstudents.personnelmanagement.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        return userMapper.login(user);
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @Override
    public int deleteUserById(Integer id) {
        return userMapper.deleteUserById(id);
    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }
}
