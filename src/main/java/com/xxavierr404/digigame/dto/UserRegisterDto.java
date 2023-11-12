package com.xxavierr404.digigame.dto;

import lombok.Data;

@Data
public class UserRegisterDto {
    private String nickname;
    private String username;
    private String password;
    private String role;
}
