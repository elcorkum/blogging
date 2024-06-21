package com.elcorkum.post_api.service;


import com.elcorkum.post_api.entity.Post;
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
}
