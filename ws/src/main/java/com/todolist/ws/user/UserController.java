package com.todolist.ws.user;

import org.springframework.web.bind.annotation.RestController;

import com.todolist.ws.error.ApiError;
import com.todolist.ws.shared.GenericMessage;

import jakarta.validation.Valid;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
public class UserController {

  @Autowired
  UserService userService;

  @PostMapping(value = "/api/v1/users")
  GenericMessage createUser(@Valid @RequestBody User user) {
    userService.save(user);
    return new GenericMessage("User is created");

  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  // @ResponseStatus(HttpStatus.BAD_REQUEST) // gerek kalmadi returnu ResponeEntity yaptigimiz icin
  ResponseEntity<ApiError> handleMethodArgNotValidException(MethodArgumentNotValidException exception) {
    ApiError apiError = new ApiError();
    apiError.setPath("/api/v1/users");
    apiError.setMessage("Validation error");
    apiError.setStatus(400);
    Map<String, String> validationError = new HashMap<>();
    for (var fieldError : exception.getBindingResult().getFieldErrors()) {
      validationError.put(fieldError.getField(), fieldError.getDefaultMessage());
    }
    // var validationError = exception.getBindingResult().getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)); // yukardakinin alternatif versioyunu
    apiError.setValidationErrors(validationError);
    return ResponseEntity.badRequest().body(apiError);

  }

}
