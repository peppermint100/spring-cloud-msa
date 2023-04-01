package com.peppermint100.userservice.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RequestUser {

    @NotNull(message = "Email is empty!")
    @Size(min = 2, message = "Email cannot be less than two characters")
    private String email;

    @NotNull(message = "Name is empty!")
    @Size(min = 2, message = "Name cannot be less than two characters")
    private String name;

    @NotNull(message = "Password is empty!")
    @Size(min = 8, message = "Password must be equal or greater than two characters")
    private String pwd;
}
