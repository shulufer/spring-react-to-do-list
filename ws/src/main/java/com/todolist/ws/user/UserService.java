package com.todolist.ws.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  UserRepository userRepository;

  PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  public void save(User user) {
    try {
      String encodedPassword = passwordEncoder.encode(user.getPassword());
      user.setPassword(encodedPassword);
      userRepository.save(user);
    } catch (DataIntegrityViolationException exception) {
      throw new NotUniqueEmailException();
    }
  }

}
