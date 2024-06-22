package com.elcorkum.post_api.repository;

import com.elcorkum.post_api.entity.PostComment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PostCommentRepository extends CrudRepository<PostComment, Long> {
    @Query(value = "select c.* from post_comment c where c.post_id = ?", nativeQuery = true)
    Iterable<PostComment> findCommentsByPost(Long postId);
}
