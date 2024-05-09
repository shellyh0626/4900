package com.bcstudents.personnelmanagement.mapper;

import com.bcstudents.personnelmanagement.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository

public interface UserMapper {
    User login(User user);

    User getUserById(Integer id);

    List<User> getAllUsers();

    int deleteUserById(Integer id);

    int addUser(User user);

    int updateUser(User user);
}
