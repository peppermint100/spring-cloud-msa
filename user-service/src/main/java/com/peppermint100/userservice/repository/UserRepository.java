package com.peppermint100.userservice.repository;

import com.peppermint100.userservice.jpa.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByUserId(String userId);
}
