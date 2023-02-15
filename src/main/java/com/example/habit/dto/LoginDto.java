package com.example.habit.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "LoginDto", description = "用户登录信息")
public class LoginDto {
    @Schema(description = "用户名")
    private String username;

    @Schema(description = "密码")
    private String password;
}
