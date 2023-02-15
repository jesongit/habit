package com.example.habit.service;

import com.example.habit.entity.Habit;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author posase
 * @since 2023-02-14
 */
public interface HabitService extends IService<Habit> {

    List<Habit> getHabitList(Long userId);

    Habit checkIn(String uid);
}
