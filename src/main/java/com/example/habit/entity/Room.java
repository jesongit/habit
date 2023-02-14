package com.example.habit.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author posase
 * @since 2023-02-14
 */
@Getter
@Setter
@TableName("t_room")
@Schema(name = "Room", description = "$!{table.comment}")
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "用户id")
    @TableId(value = "user_id", type = IdType.INPUT)
    private Long userId;

    @Schema(description = "房间名")
    private String name;

    @Schema(description = "简介描述")
    private String descr;

    @Schema(description = "房间密码")
    private String psasword;

    @Schema(description = "房间人员")
    private String userList;
}
