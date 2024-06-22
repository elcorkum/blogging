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
//@RequestMapping("/users/{userId}/posts")
public class PostController {

    private final PostService postService;
    private final Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }


    @PostMapping("/users/{userId}/posts")
    public ResponseEntity<?> createNewPost(@RequestBody Post post){
        Post newPost = postService.createPost(post);
        if(newPost == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        logger.info("PostController createNewPost() output {}", newPost);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @GetMapping("/users/{userId}/posts/{postId}")
    public ResponseEntity<?> getExistingPostById(@PathVariable Long postId){
        Post post = postService.getPostById(postId);
        if(post == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        logger.info("PostController getPostById() output {}", post);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("/posts/{userId}")
    public ResponseEntity<?> getAllExistingPostsByAccount(@PathVariable Long userId){
        Iterable<Post> posts = postService.getAllPostsByAccount(userId);
        if (!posts.iterator().hasNext())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        logger.info("PostController getExistingPostsByAccount() output {}", posts);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<?> getAllExistingPosts(){
        Iterable<Post> allPosts = postService.getAllPosts();
        if (!allPosts.iterator().hasNext())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        logger.info("PostController getAllExistingPosts() output {}", allPosts);
        return new ResponseEntity<>(allPosts, HttpStatus.OK);
    }

    @PutMapping("/users/{userId}/posts/{postId}")
    public ResponseEntity<?> updateExistingPost(@PathVariable Long postId, @RequestBody Post post){
        Post updatedPost = postService.updatePost(postId, post);
        if(post == null)
            return ResponseHandler.responseBuilder("Failed to update post", HttpStatus.NOT_FOUND);
        logger.info("PostController updateExistingPost() output {}", updatedPost);
        return ResponseHandler.responseBuilder("Updated successfully", HttpStatus.OK, updatedPost);
    }

    @DeleteMapping("/users/{userId}/posts/{postId}")
    public ResponseEntity<?> deleteExistingPost(@PathVariable Long postId){
        postService.deletePost(postId);
        Post post = postService.getPostById(postId);
        if(post == null)

            return ResponseHandler.responseBuilder("Successfully deleted post!", HttpStatus.NO_CONTENT);
        logger.error("PostController deleteExistingPost() fail");
        return ResponseHandler.responseBuilder("Failed to delete post", HttpStatus.NOT_FOUND);
    }

}
