package com.istad.dataanalyticrestfulapi.service;

import com.istad.dataanalyticrestfulapi.model.Request.UserRequest;
import com.istad.dataanalyticrestfulapi.model.User;
import com.istad.dataanalyticrestfulapi.model.UserAccount;

import java.util.List;

public interface UserService {
    List<User> allUsers();
    List<User> findUserByUsername();
    int createNewUser(UserRequest request);
    int updateUser(User user, int id);
    User findUserByID(int id);
    int removeUser(UserRequest id);
    List<UserAccount> getAllUserAccount();

    int removeUser(int id);
}
