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
 * 计时器表
 * </p>
 *
 * @author posase
 * @since 2023-02-14
 */
@Getter
@Setter
@TableName("t_timer")
@Schema(name = "Timer", description = "$!{table.comment}")
public class Timer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "uid")
    @TableId(value = "uid", type = IdType.ASSIGN_UUID)
    private String uid;

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "开始时间")
    private LocalDateTime startTime;

    @Schema(description = "持续时间")
    private Integer duration;
}
