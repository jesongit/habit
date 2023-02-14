package com.example.habit.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "UserInfo", description = "用户基本信息")
public class UserInfo {

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "头像")
    private String picture;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "昵称")
    private String nickname;
}
