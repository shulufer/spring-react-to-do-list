package com.todolist.ws.user.validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.todolist.ws.user.User;
import com.todolist.ws.user.UserRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String>{

  @Autowired
  UserRepository userRepository;

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    User inDatabase = userRepository.findByEmail(value);
    return inDatabase == null;
  }

}
