package com.elcorkum.post_api.service;


import com.elcorkum.post_api.entity.PostComment;
import com.elcorkum.post_api.exception.ResourceNotFoundException;
import com.elcorkum.post_api.repository.PostCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostCommentService {
    private final PostCommentRepository postCommentRepository;

    @Autowired
    public PostCommentService(PostCommentRepository postCommentRepository) {
        this.postCommentRepository = postCommentRepository;
    }

    public PostComment getPostCommentById(Long postCommentId){
        return postCommentRepository.findById(postCommentId).orElse(null);
    }

    public PostComment createPostComment(PostComment postComment){
        return postCommentRepository.save(postComment);
    }

    public Iterable<PostComment> getAllPostComments (){
        return postCommentRepository.findAll();
    }

    public Iterable<PostComment> getPostCommentsByPost(Long postId){
        return postCommentRepository.findCommentsByPost(postId);
    }

    public void deletePostComment(Long postCommentId){
        verifyPostComment(postCommentId);
        postCommentRepository.deleteById(postCommentId);
    }

    public PostComment updatePostComment(Long postCommentId, PostComment postComment){
        verifyPostComment(postCommentId);
        PostComment updatedComment = null;
        for(PostComment comment :postCommentRepository.findAll()){
            if(comment.getId().equals(postCommentId)){
                updatedComment = postCommentRepository.save(postComment);
            }
        }
        return updatedComment;
    }

    protected void verifyPostComment(Long postCommentId){
        PostComment postComment = postCommentRepository.findById(postCommentId).orElse(null);
        if(postComment == null)
            throw new ResourceNotFoundException("Comment with ID: " + postCommentId + " not found");
    }
}
