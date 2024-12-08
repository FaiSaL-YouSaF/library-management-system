package com.faisalyousaf777.controller;


import com.faisalyousaf777.entity.User;
import com.faisalyousaf777.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable("userId") final Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/save")
    public ResponseEntity<Object> saveUser(@RequestBody final User user) {
        userService.addUser(user);
        return ResponseEntity.ok("User saved successfully.");
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Object> updateUserById(@PathVariable("userId") final Long userId, @RequestBody final User user) {
        userService.updateUserById(userId, user);
        return ResponseEntity.ok("User updated successfully.");
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUserById(@PathVariable("userId") final Long userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.ok("User deleted successfully.");
    }
}
