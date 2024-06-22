package com.elcorkum.post_api.controller;

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
        try {
            PostComment newComment = postCommentService.createPostComment(postComment);
            if(newComment != null)
                logger.info("PostCommentController createNewPost() success {}", newComment);
            return ResponseHandler.responseBuilder("Successfully saved comment", HttpStatus.CREATED, newComment);
        } catch (Exception e) {
            logger.info("PostCommentController createNewPost() failed request");
            return ResponseHandler.responseBuilder("Unable to save comment", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/postcomments/{postCommentId}")
    public ResponseEntity<?> getExistingPostCommentById(@PathVariable Long postCommentId){
        try {
            PostComment postComment = postCommentService.getPostCommentById(postCommentId);
            if(postComment != null)
                logger.info("PostCommentController getExistingPostCommentById() success {}", postComment);
            return ResponseHandler.responseBuilder("Successfully retrieved comment", HttpStatus.OK, postComment);
        } catch (Exception e) {
            logger.info("PostCommentController getExistingPostCommentById() failed request");
            return ResponseHandler.responseBuilder("Comment not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/postcomments")
    public ResponseEntity<?> getAllExistingPostComments(){
        try {
            Iterable<PostComment> allComments = postCommentService.getAllPostComments();
            if(allComments.iterator().hasNext())
                logger.info("PostController getAllExistingPostComments()  SUCCESS! {}", allComments);
            return ResponseHandler.responseBuilder("Successfully retrieved comments.", HttpStatus.OK, allComments);
        } catch (Exception e) {
            logger.info("PostController getAllExistingPostComments()  failed request");
            return ResponseHandler.responseBuilder("Comments not found", HttpStatus.NOT_FOUND);

        }
    }

    @GetMapping("posts/{postId}/postcomments")
    public ResponseEntity<?> getAllPostCommentsByPost(@PathVariable Long postId){
        try {
            Iterable<PostComment> comments = postCommentService.getPostCommentsByPost(postId);
            if(comments.iterator().hasNext())
                logger.info("PostController getAllPostCommentsByPost() SUCCESS! {}", comments);
            return ResponseHandler.responseBuilder("Successfully retrieved comments.", HttpStatus.OK, comments);
        } catch (Exception e) {
            logger.info("PostController getAllPostCommentsByPost() failed request");
            return ResponseHandler.responseBuilder("Comments not found", HttpStatus.NOT_FOUND);
        }
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

    @PutMapping("postcomments/{postCommentId}")
    public ResponseEntity<?> updateExistingPostComment(@PathVariable Long postCommentId, @RequestBody PostComment postComment){
        try {
            PostComment updatedComment = postCommentService.updatePostComment(postCommentId, postComment);
            if(updatedComment != null)
                logger.info("PostCommentController updateExistingPostComment() update successful {}", updatedComment);
            return ResponseHandler.responseBuilder("Update successful", HttpStatus.ACCEPTED, updatedComment);
        } catch (Exception e) {
            logger.info("PostCommentController updateExistingPostComment() failed update");
            return ResponseHandler.responseBuilder("Comment not found", HttpStatus.NOT_FOUND);
        }
    }

}
