package com.faisalyousaf777.service;

import com.faisalyousaf777.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void addUser(final User user);
    Optional<User> getUserById(final Long id);
    Optional<List<User>> getAllUsers();
    void updateUserById(final Long id, final User user);
    void deleteUserById(final Long id);
}
