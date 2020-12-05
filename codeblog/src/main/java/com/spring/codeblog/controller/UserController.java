package com.spring.codeblog.controller;

import com.spring.codeblog.model.LoginDto;
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
    ResponseEntity<Response<User>> createUser(@Valid @RequestBody User user) throws Exception {

        Response<User> response = new Response<>(true);

        response.setData(userService.criarUsuario(user));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(value = "/login")
    ResponseEntity<Response<User>> login(@Valid @RequestBody LoginDto loginDto) throws Exception {

        Response<User> response = new Response<>(true);

        response.setData(userService.login(loginDto));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
