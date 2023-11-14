package com.todolist.ws.user;

import org.springframework.web.bind.annotation.RestController;

import com.todolist.ws.error.ApiError;
import com.todolist.ws.shared.GenericMessage;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class UserController {

  @Autowired
  UserService userService;

  @PostMapping(value = "/api/v1/users")
  ResponseEntity<?> createUser(@RequestBody User user) {
    ApiError apiError = new ApiError();
    apiError.setPath("/api/v1/users");
    apiError.setMessage("Validation error");
    apiError.setStatus(400);
    Map<String, String> validationError = new HashMap<>();
    if (user.getUsername() == null || user.getUsername().isEmpty()) {
      validationError.put("username", "Username connot be null.");
    }

    if (user.getEmail() == null || user.getEmail().isEmpty()) {
      validationError.put("email", "E-mail connot be null.");
    }

    if(validationError.size() > 0) {
      apiError.setValidationErrors(validationError);
      return ResponseEntity.badRequest().body(apiError);
    }
    userService.save(user);
    return ResponseEntity.ok(new GenericMessage("User is created"));

  }

}
