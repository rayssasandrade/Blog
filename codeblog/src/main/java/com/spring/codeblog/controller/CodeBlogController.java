package com.spring.codeblog.controller;

import com.spring.codeblog.model.Post;
import com.spring.codeblog.model.User;
import com.spring.codeblog.service.CodeblogService;
import com.spring.codeblog.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
public class CodeBlogController {

    @Autowired
    CodeblogService codeblogService;

    @GetMapping(value = "/posts")
    ResponseEntity<Response<List<Post>>> getPosts() throws Exception {

        Response<List<Post>> response = new Response<>(true);

        response.setData(codeblogService.getAllPosts());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(value = "/post/{id}")
    ResponseEntity<Response<Post>> getPostById(@PathVariable("id") long id) throws Exception {

        Response<Post> response = new Response<>(true);

        response.setData(codeblogService.getPostById(id));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @PostMapping(value = "/newpost")
    ResponseEntity<Response<Post>> savePost(@Valid @RequestBody Post post) throws Exception {

        Response<Post> response = new Response<>(true);

        response.setData(codeblogService.save(post));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}