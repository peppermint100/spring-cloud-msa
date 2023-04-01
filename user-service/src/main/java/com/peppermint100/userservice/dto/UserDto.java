package com.peppermint100.userservice.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
    private String email;
    private String name;
    private String pwd;
    private Date createdAt;
    private String userId;
    private String encryptedPwd;
}
