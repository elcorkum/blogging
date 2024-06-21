package com.elcorkum.post_api.repository;

import com.elcorkum.post_api.entity.PostComment;
import org.springframework.data.repository.CrudRepository;

public interface PostCommentRepository extends CrudRepository<PostComment, Long> {
}
