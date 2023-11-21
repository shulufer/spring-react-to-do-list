package com.todolist.ws.user;


import com.todolist.ws.user.validation.UniqueEmail;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
public class User {

  @Id
  @GeneratedValue
  long id;

  @NotBlank(message = "{todolist.constraints.NotBlank.message}")
  @Size(min = 4, max = 32)
  String username;

  @NotBlank
  @Email
  @UniqueEmail
  String email;

  @Pattern(
    regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$",
    message = "{todolist.constraints.Pattern.message}"
    )
    String password;


  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
