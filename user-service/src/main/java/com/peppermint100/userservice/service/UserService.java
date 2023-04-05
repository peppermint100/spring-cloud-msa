package com.peppermint100.userservice.service;

import com.peppermint100.userservice.dto.UserDto;
import com.peppermint100.userservice.jpa.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDto createUser(UserDto userDto);
    UserDto getUserById(String userId);
    Iterable<UserEntity> getUserByAll();

    UserDto getUserDetailsByEmail(String email);
}
