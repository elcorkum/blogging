package com.elcorkum.post_api.repository;

import com.elcorkum.post_api.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
