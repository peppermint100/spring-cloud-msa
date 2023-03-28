package com.example.firstservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/first-service")
@Slf4j
public class FirstServiceController {

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to The First Service";
    }

    @GetMapping("/message")
    public String message(
            @RequestHeader(value = "first-request", required = false) String header
    ) {
        log.info("header = " + header);
        return "message";
    }

    @GetMapping("/check")
    public String check() {
        return "First Service Checked";
    }
}
