package com.istad.dataanalyticrestfulapi.service.serviceImpl;

import com.istad.dataanalyticrestfulapi.model.Request.UserRequest;
import com.istad.dataanalyticrestfulapi.model.User;
import com.istad.dataanalyticrestfulapi.model.UserAccount;
import com.istad.dataanalyticrestfulapi.repository.UserRepository;
import com.istad.dataanalyticrestfulapi.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public List<User> allUsers() {
        return userRepository.allUsers();
    }

    @Override
    public List<User> findUserByUsername() {
        return userRepository.findUserByUsername();
    }

    @Override
    public int createNewUser(UserRequest request) {
        return userRepository.createNewUser(request);
    }

    @Override
    public int updateUser(User user, int id) {
        return userRepository.updateUser(user, id);
    }

    @Override
    public User findUserByID(int id) {
        return userRepository.findUserByID(id);
    }

    @Override
    public int removeUser(UserRequest id) {
        return userRepository.removeUser(id);
    }

    @Override
    public List<UserAccount> getAllUserAccount() {
        return userRepository.getAllUserAccount();
    }

    @Override
    public int removeUser(int id) {
        return 0;
    }
}