package com.peppermint100.userservice.service;

import com.peppermint100.userservice.dto.UserDto;

public interface UserService {

    UserDto createUser(UserDto userDto);
}
