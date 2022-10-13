package com.staxrt.tutorial.controller;

import com.staxrt.tutorial.model.User;
import com.staxrt.tutorial.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1")
public class UserController {
  @Autowired
  UserService userService;

  @GetMapping("/users")
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }

  @PostMapping("/users")
  public ResponseEntity<User> createUser(@Valid @RequestBody User user) throws Exception{
    return userService.createUser(user);
  }

  @DeleteMapping("/users/{id}")
  public void deleteUser(@PathVariable(value = "id") Long userId) throws Exception {
    userService.deleteUser(userId);
  }

  @GetMapping("/users/{id}")
  public ResponseEntity<User> findUserById(@PathVariable(value = "id") Long userId) throws Exception {
    return userService.findUserById(userId);
  }

  @PutMapping("/users/{id}")
  public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId, @Valid @RequestBody User userDetails) throws
          Exception {
    return userService.updateUser(userId, userDetails);
  }
}
