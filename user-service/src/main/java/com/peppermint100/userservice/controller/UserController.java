package com.peppermint100.userservice.controller;

import com.peppermint100.userservice.dto.UserDto;
import com.peppermint100.userservice.service.UserService;
import com.peppermint100.userservice.vo.Greeting;
import com.peppermint100.userservice.vo.RequestUser;
import com.peppermint100.userservice.vo.ResponseUser;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {

    private final Greeting greeting;
    private final UserService service;

    public UserController(Greeting greeting, UserService service) {
        this.greeting = greeting;
        this.service = service;
    }

    @GetMapping("/health-check")
    public String status() {
        return "User Service is Working";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return greeting.getMessage();
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser user) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto userDto = mapper.map(user, UserDto.class);
        service.createUser(userDto);
        ResponseUser responseUser = mapper.map(userDto, ResponseUser.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }
}