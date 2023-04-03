package com.peppermint100.userservice.dto;

import com.peppermint100.userservice.vo.ResponseOrder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserDto {
    private String email;
    private String name;
    private String pwd;
    private Date createdAt;
    private String userId;
    private String encryptedPwd;
    private List<ResponseOrder> orders;
}
