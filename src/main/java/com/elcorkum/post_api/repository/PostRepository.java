package com.elcorkum.post_api.repository;

import com.elcorkum.post_api.entity.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
