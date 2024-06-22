package com.elcorkum.post_api.controller;

import com.elcorkum.post_api.entity.Post;
import com.elcorkum.post_api.entity.PostComment;
import com.elcorkum.post_api.response.ResponseHandler;
import com.elcorkum.post_api.service.PostCommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class PostCommentController {

    private final PostCommentService postCommentService;
    private Logger logger = LoggerFactory.getLogger(PostCommentController.class);


    @Autowired
    public PostCommentController(PostCommentService postCommentService) {
        this.postCommentService = postCommentService;
    }

    @PostMapping("posts/{postId}/postcomments")
    public ResponseEntity<?> createNewPost (@RequestBody PostComment postComment){
        PostComment newComment = postCommentService.createPostComment(postComment);
        if(newComment == null)
            return ResponseHandler.responseBuilder("Unable to save comment", HttpStatus.NOT_FOUND);
        logger.info("PostCommentController createNewPost() {}", newComment);
        return ResponseHandler.responseBuilder("Successfully saved comment", HttpStatus.CREATED, newComment);
    }

    @GetMapping("/postcomments/{postCommentId}")
    public ResponseEntity<?> getExistingPostCommentById(@PathVariable Long postCommentId){
        PostComment postComment = postCommentService.getPostCommentById(postCommentId);
        if(postComment == null)
            return ResponseHandler.responseBuilder("Comment not found", HttpStatus.NOT_FOUND);
        logger.info("PostCommentController getExistingPostCommentById() {}", postComment);
        return ResponseHandler.responseBuilder("Successfully retrieved comment", HttpStatus.OK, postComment);
    }

    @GetMapping("/postcomments")
    public ResponseEntity<?> getAllExistingPostComments(){
        Iterable<PostComment> allComments = postCommentService.getAllPostComments();
        if(!allComments.iterator().hasNext())
            return ResponseHandler.responseBuilder("Comments not found", HttpStatus.NOT_FOUND);
        logger.info("PostController getAllExistingPostComments() {}", allComments);
        return ResponseHandler.responseBuilder("Successfully retrieved comments.", HttpStatus.OK, allComments);
    }

    @GetMapping("posts/{postId}/postcomments")
    public ResponseEntity<?> getAllPostCommentsByPost(@PathVariable Long postId){
        Iterable<PostComment> comments = postCommentService.getPostCommentsByPost(postId);
        if(!comments.iterator().hasNext())
            return ResponseHandler.responseBuilder("Comments not found", HttpStatus.NOT_FOUND);
        logger.info("PostController getAllPostCommentsByPost() {}", comments);
        return ResponseHandler.responseBuilder("Successfully retrieved comments.", HttpStatus.OK, comments);
    }

    @DeleteMapping("postcomments/{postCommentId}")
    public ResponseEntity<?> deletePostCommentById(@PathVariable Long postCommentId){
        try {
            postCommentService.deletePostComment(postCommentId);
            logger.info("PostCommentController deletePostByCommentId() successful delete");
            return ResponseHandler.responseBuilder("Delete successful", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.info("PostCommentController deletePostByCommentId() failed deleted");
            return ResponseHandler.responseBuilder("Unable to delete", HttpStatus.NOT_FOUND);
        }
    }
}
