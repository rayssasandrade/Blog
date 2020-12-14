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
    ResponseEntity<List<Post>> getPosts() throws Exception {

        List<Post> response = codeblogService.getAllPosts();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(value = "/post/{id}")
    ResponseEntity<Post> getPostById(@PathVariable("id") long id) throws Exception {

        Post response = codeblogService.getPostById(id);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @PostMapping(value = "/v1/newpost")
    ResponseEntity<Post> savePost(@Valid @RequestBody Post post) throws Exception {

        Post response = codeblogService.save(post);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(value = "/v1/{id}/updatepost")
    ResponseEntity<Post> updatePost(@RequestBody Post post, @PathVariable("id") long id) throws Exception {

        Post response = codeblogService.updatePost(id, post);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(value = "/v1/{id}/deletepost")
    ResponseEntity<Post> deletePost(@PathVariable("id") long id) throws Exception {

        Post response = codeblogService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}