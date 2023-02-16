package com.example.habit.common.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum HabitType implements IEnum<Integer> {
    DEFAULT(0, "默认"),
    STUDY(1, "学习"),
    SPORT(2, "运动"),
    OTHER(3, "其他"),
    ;

    private final int value;
    private final String message;

    @Override
    public Integer getValue() {
        return this.value;
    }
}
