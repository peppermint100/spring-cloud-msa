package com.peppermint100.userservice.controller;

import com.peppermint100.userservice.dto.UserDto;
import com.peppermint100.userservice.jpa.UserEntity;
import com.peppermint100.userservice.service.UserService;
import com.peppermint100.userservice.vo.Greeting;
import com.peppermint100.userservice.vo.RequestUser;
import com.peppermint100.userservice.vo.ResponseUser;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    private final Greeting greeting;
    private final Environment env;
    private final UserService service;

    public UserController(Greeting greeting, Environment env, UserService service) {
        this.greeting = greeting;
        this.env = env;
        this.service = service;
    }

    @GetMapping("/health-check")
    public String status() {
        return String.format(
                "User Service local.server.port = " + env.getProperty("local.server.port") +
                "server.port = " + env.getProperty("server.port") +
                "token secret = " + env.getProperty("token.secret") +
                "token expiration time = " + env.getProperty("token.expirationTime")
                );
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

    @GetMapping("/users")
    public ResponseEntity<List<ResponseUser>> getUsers() {
        Iterable<UserEntity> userList = service.getUserByAll();

        List<ResponseUser> result = new ArrayList<>();

        userList.forEach(u -> {
            result.add(new ModelMapper().map(u, ResponseUser.class));
        });

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/users/{userId}")
    private ResponseEntity<ResponseUser> getUserById(
            @PathVariable("userId") String userId
    ){
        UserDto userDto = service.getUserById(userId);

        ResponseUser user = new ModelMapper().map(userDto, ResponseUser.class);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}