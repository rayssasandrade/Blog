package com.spring.codeblog.controller;

import com.spring.codeblog.model.LoginDto;
import com.spring.codeblog.model.Post;
import com.spring.codeblog.model.User;
import com.spring.codeblog.service.UserService;
import com.spring.codeblog.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/user")
    ResponseEntity<User> createUser(@Valid @RequestBody User user) throws Exception {

        User response = userService.criarUsuario(user);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(value = "/login")
    ResponseEntity<User> login(@Valid @RequestBody LoginDto loginDto) throws Exception {

        User response = userService.login(loginDto);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(value = "/user/{id}")
    ResponseEntity<User> getUserById(@PathVariable("id") long id) throws Exception {

        User response = userService.getUserById(id);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
