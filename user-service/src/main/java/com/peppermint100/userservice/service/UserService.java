package com.peppermint100.userservice.service;

import com.peppermint100.userservice.dto.UserDto;
import com.peppermint100.userservice.jpa.UserEntity;

public interface UserService {

    UserDto createUser(UserDto userDto);
    UserDto getUserById(String userId);
    Iterable<UserEntity> getUserByAll();
}
