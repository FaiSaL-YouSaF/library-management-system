package com.faisalyousaf777.service.impl;

import com.faisalyousaf777.entity.LibraryCard;
import com.faisalyousaf777.entity.User;
import com.faisalyousaf777.exceptions.UserAlreadyExistsException;
import com.faisalyousaf777.exceptions.UserNotFoundException;
import com.faisalyousaf777.factory.LibraryCardFactory;
import com.faisalyousaf777.repository.LibraryCardRepository;
import com.faisalyousaf777.repository.UserRepository;
import com.faisalyousaf777.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final LibraryCardRepository libraryCardRepository;


    public UserServiceImpl(UserRepository userRepository, LibraryCardRepository libraryCardRepository) {
        this.userRepository = userRepository;
        this.libraryCardRepository = libraryCardRepository;
    }

    @Override
    public void addUser(final User user) {
        if (userRepository.findById(user.getUserId()).isPresent()) {
            throw new UserAlreadyExistsException("Invalid ID : User with the ID : " + user.getUserId() + " already exists.");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("Invalid Email : User with the Email : " + user.getEmail() + " already exists.");
        }
        if (userRepository.findByPhoneNumber(user.getPhoneNumber()).isPresent()) {
            throw new UserAlreadyExistsException("Invalid Phone Number : User with the Phone Number : " + user.getPhoneNumber() + " already exists.");
        }
        log.info("Generating a new library card for the user with the email : {}", user.getEmail());
        LibraryCard libraryCard = LibraryCardFactory.createLibraryCard();
        libraryCard.setUser(user);
        user.setLibraryCard(libraryCard);
        log.info("Registering a new user with the email : {}", user.getEmail());
        userRepository.save(user);
        libraryCardRepository.save(libraryCard);
    }

    @Override
    public Optional<User> getUserById(final Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("Invalid ID : User with the ID : " + id + " does not exists.");
        }
        return userRepository.findById(id);
    }

    @Override
    public Optional<List<User>> getAllUsers() {
        return Optional.of(userRepository.findAll());
    }

    @Override
    public void updateUserById(final Long id, final User user) {
        if (userRepository.findById(id).isEmpty()) {
            throw new UserNotFoundException("Invalid ID : User with the ID : " + id + " does not exists.");
        } else {
            User currentUser = userRepository.findById(id).get();
            currentUser.setUserName(user.getUserName());
            log.info("Updating the user with the user-name : {}", currentUser.getUserName());
            currentUser.setNationalIdentityCardNumber(user.getNationalIdentityCardNumber());
            currentUser.setFirstName(user.getFirstName());
            currentUser.setLastName(user.getLastName());
            currentUser.setEmail(user.getEmail());
            currentUser.setPhoneNumber(user.getPhoneNumber());
            currentUser.setDateOfBirth(user.getDateOfBirth());
            currentUser.setPassword(user.getPassword());
            userRepository.save(currentUser);
        }
    }

    @Override
    public void deleteUserById(final Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("Invalid ID : User with the ID : " + id + " does not exists.");
        } else {
            log.info("Deleting the user with the ID : {}", id);
            userRepository.deleteById(id);
        }
    }
}
