package com.example.habit.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
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
@TableName("t_chat")
@Schema(name = "Chat", description = "$!{table.comment}")
public class Chat implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "uid")
    @TableId(value = "uid", type = IdType.ASSIGN_UUID)
    private String uid;

    @Schema(description = "房主id")
    private Long userId;

    @Schema(description = "发送人id")
    private Long sendId;

    @Schema(description = "发送时间")
    private LocalDateTime sendTime;

    @Schema(description = "消息内容")
    private String content;
}
