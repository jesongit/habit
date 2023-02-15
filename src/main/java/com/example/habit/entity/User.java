package com.example.habit.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author posase
 * @since 2023-02-14
 */
@Data
@NoArgsConstructor
@TableName("t_user")
@Schema(name = "User", description = "用户")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "用户id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "会员等级")
    private Integer vipLevel;

    @Schema(description = "积分")
    private Long points;

    @Schema(description = "连续签到天数")
    private Integer serialSign;

    @Schema(description = "总签到天数")
    private Integer totalSign;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "头像url")
    private String picture;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
