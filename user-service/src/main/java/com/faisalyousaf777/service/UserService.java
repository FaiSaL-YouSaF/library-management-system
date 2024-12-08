package com.faisalyousaf777.service;

import com.faisalyousaf777.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public void addUser(final User user);
    public Optional<User> getUserById(final Long id);
    public Optional<List<User>> getAllUsers();
    public void updateUserById(final Long id, final User user);
    public void deleteUserById(final Long id);
}
