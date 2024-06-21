package com.elcorkum.post_api.controller;


import com.elcorkum.post_api.entity.Post;
import com.elcorkum.post_api.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/{userId}/posts")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }


    @PostMapping()
    public ResponseEntity<?> createNewPost(@RequestBody Post post){
        Post newPost = postService.createPost(post);
        if(newPost == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }
}
