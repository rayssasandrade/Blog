package com.spring.codeblog.service;

import com.spring.codeblog.model.Post;
import com.spring.codeblog.repository.CodeBlogRepository;
import com.spring.codeblog.service.interfaces.ICodeBlog;
import com.spring.codeblog.utils.Errors;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CodeblogService implements ICodeBlog {

    @Autowired
    CodeBlogRepository codeBlogRepository;

    private static final String INTERNAL_SERVER_ERROR_MSG = "Internal Server Error, please contact our support";

    @Override
    public List<Post> getAllPosts() {
        try {
            return codeBlogRepository.findAll();
        } catch (Errors customError) {
            throw customError;
        } catch (Exception error) {
            throw new Errors(INTERNAL_SERVER_ERROR_MSG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Post getPostById(long id) {
        try {
            Optional<Post> post = codeBlogRepository.findById(id);

            if (!post.isPresent()) {
                throw new Errors("Post não encontrado !", HttpStatus.NOT_FOUND);
            }

            return post.get();
        } catch (Errors customError) {
            throw customError;
        } catch (Exception error) {
            throw new Errors(INTERNAL_SERVER_ERROR_MSG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Post save(Post post) {
        try {
            return codeBlogRepository.save(post);
        } catch (Errors customError) {
            throw customError;
        } catch (Exception error) {
            throw new Errors(INTERNAL_SERVER_ERROR_MSG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
