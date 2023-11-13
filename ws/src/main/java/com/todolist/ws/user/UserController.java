package com.todolist.ws.user;

import org.springframework.web.bind.annotation.RestController;

import com.todolist.ws.shared.GenericMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class UserController {

  @Autowired
  UserService userService;

  @PostMapping(value = "/api/v1/users")
  GenericMessage createUser(@RequestBody User user) {
    userService.save(user);
    return new GenericMessage("User is created");

  }

}
