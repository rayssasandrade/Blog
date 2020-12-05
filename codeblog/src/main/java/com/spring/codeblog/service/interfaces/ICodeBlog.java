package com.spring.codeblog.service.interfaces;

import com.spring.codeblog.model.Post;

import java.util.List;

public interface ICodeBlog {

    List<Post> getAllPosts();
    Post getPostById(long id);
    Post save(Post post);

}
