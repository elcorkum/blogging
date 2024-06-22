package com.elcorkum.post_api.repository;

import com.elcorkum.post_api.entity.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
    @Query(value = "select p.* from post p, user u where u.id = ? and p.user_id = u.id", nativeQuery = true)
    Iterable<Post> getAllPostsByAccount(Long userId);

}
