package com.elcorkum.post_api.service;


import com.elcorkum.post_api.entity.Post;
import com.elcorkum.post_api.exception.ResourceNotFoundException;
import com.elcorkum.post_api.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(Post post){
        return postRepository.save(post);
    }

    public Post getPostById(Long postId){
        verifyPost(postId);
        return postRepository.findById(postId).orElse(null);
    }

    public Iterable<Post> getAllPostsByAccount(Long userId){
        return postRepository.getAllPostsByAccount(userId);
    }

    public Iterable<Post> getAllPosts(){
        return postRepository.findAll();
    }

    public void deletePost(Long postId){
        verifyPost(postId);
        postRepository.deleteById(postId);
    }

    public Post updatePost(Long postId, Post post){
        verifyPost(postId);
        Post updatedPost = null;
        for(Post p: postRepository.findAll()) {
            if (p.getId().equals(postId))
                updatedPost = postRepository.save(post);
        }
        return updatedPost;
    }



    protected void verifyPost(Long postId){
        Post post = postRepository.findById(postId).orElse(null);
        if(post == null){
            throw new ResourceNotFoundException("Post with ID: " + postId + " not found.");
        }
    }
}
