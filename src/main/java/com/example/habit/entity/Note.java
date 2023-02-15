package com.example.habit.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * 
 * @TableName t_note
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@TableName(value ="t_note")
public class Note implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "uid")
    @TableId(value = "uid", type = IdType.ASSIGN_UUID)
    private String uid;

    @NonNull
    @Schema(description = "发布者id")
    private Long userId;

    @NonNull
    @Schema(description = "图片列表")
    private String images;

    @NonNull
    @Schema(description = "内容")
    private String content;

    @Schema(description = "点赞用户列表")
    @TableField(fill = FieldFill.INSERT)
    private String likeList;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}