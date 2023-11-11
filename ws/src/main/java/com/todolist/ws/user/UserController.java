package com.todolist.ws.user;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
public class UserController {

    @PostMapping(value="/api/v1/users")
    public void createUser() {

    }

}
