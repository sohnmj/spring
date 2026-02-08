package com.example.oauth2authorization.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private String username;
    private String password;
    private String role;
    private String nickname;
    private String phone;
}
