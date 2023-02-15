package com.example.habit.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.example.habit.common.Utils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

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
@RequiredArgsConstructor
@TableName("t_habit")
@Schema(name = "Habit", description = "习惯")
public class Habit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "uid")
    @TableId(value = "uid", type = IdType.ASSIGN_UUID)
    private String uid;

    @NonNull
    @Schema(description = "用户id")
    private Long userId;

    @NonNull
    @Schema(description = "习惯名字")
    private String name;

    @Schema(description = "连续打卡天数")
    private Integer serialDay;

    @Schema(description = "月打卡记录")
    private Integer record;

    @Schema(description = "打卡日期")
    private LocalDateTime recordTime;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    public void check_in(int day, boolean serial) {
        this.record = Utils.bit_set(record, day);
        this.serialDay = serial ? this.serialDay + 1 : 0;
    }
}
