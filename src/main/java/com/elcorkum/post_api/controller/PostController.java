package com.elcorkum.post_api.controller;


import com.elcorkum.post_api.response.ResponseHandler;
import com.elcorkum.post_api.entity.Post;
import com.elcorkum.post_api.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {

    private final PostService postService;
    private final Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }


    @PostMapping("/users/{userId}/posts")
    public ResponseEntity<?> createNewPost(@RequestBody Post post){
        try {
            Post newPost = postService.createPost(post);
            if(newPost != null)
                logger.info("PostController createNewPost() success! {}", newPost);
            return ResponseHandler.responseBuilder("Post saved successfully", HttpStatus.OK, newPost);
        } catch (Exception e) {
            logger.info("PostController createNewPost() failed request");
            return ResponseHandler.responseBuilder("Post not saved", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users/{userId}/posts/{postId}")
    public ResponseEntity<?> getExistingPostById(@PathVariable Long postId){
        try {
            Post post = postService.getPostById(postId);
            if(post != null)
                logger.info("PostController getPostById() success! {}", post);
            return ResponseHandler.responseBuilder("Post retrieved successfully", HttpStatus.OK);
        } catch (Exception e) {
            logger.info("PostController getPostById() failed request");
            return ResponseHandler.responseBuilder("Post not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/posts/{userId}")
    public ResponseEntity<?> getAllExistingPostsByAccount(@PathVariable Long userId){
        try {
            Iterable<Post> posts = postService.getAllPostsByAccount(userId);
            if (posts.iterator().hasNext())
                logger.info("PostController getExistingPostsByAccount() success {}", posts);
            return ResponseHandler.responseBuilder("Posts retrieved successfully", HttpStatus.OK, posts);
        } catch (Exception e) {
            logger.info("PostController getExistingPostsByAccount() failed request");
            return ResponseHandler.responseBuilder("Posts not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/posts")
    public ResponseEntity<?> getAllExistingPosts(){
        try {
            Iterable<Post> allPosts = postService.getAllPosts();
            if (allPosts.iterator().hasNext())
                logger.info("PostController getAllExistingPosts() success! {}", allPosts);
            return ResponseHandler.responseBuilder("Posts retrieved successfully", HttpStatus.OK, allPosts);
        } catch (Exception e) {
            logger.info("PostController getAllExistingPosts() failed request");
            return ResponseHandler.responseBuilder("Posts not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/users/{userId}/posts/{postId}")
    public ResponseEntity<?> updateExistingPost(@PathVariable Long postId, @RequestBody Post post){
        try {
            Post updatedPost = postService.updatePost(postId, post);
            if(post != null)
                logger.info("PostController updateExistingPost() success! {}", updatedPost);
            return ResponseHandler.responseBuilder("Updated successfully", HttpStatus.ACCEPTED, updatedPost);
        } catch (Exception e) {
            logger.info("PostController updateExistingPost() failed request");
            return ResponseHandler.responseBuilder("Post not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/users/{userId}/posts/{postId}")
    public ResponseEntity<?> deleteExistingPost(@PathVariable Long postId){
        try {
            postService.deletePost(postId);
            logger.info("PostController deleteExistingPost() successful delete");
            return ResponseHandler.responseBuilder("Successfully deleted post!", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.error("PostController deleteExistingPost() failed request");
            return ResponseHandler.responseBuilder("Post not found", HttpStatus.NOT_FOUND);
        }
    }


}
