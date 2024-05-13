package com.bcstudents.personnelmanagement.service;

import com.bcstudents.personnelmanagement.bean.Salary;
import com.bcstudents.personnelmanagement.bean.User;

import java.util.List;
public interface IUserService {
    //    Backend login
    User login(User user);

    User getUserById(Integer id);

    List<User> getAllUsers();

    int deleteUserById(Integer id);

    int addUser(User user);

    int updateUser(User user);
}
