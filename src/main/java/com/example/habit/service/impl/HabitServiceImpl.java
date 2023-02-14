package com.example.habit.service.impl;

import com.example.habit.common.Utils;
import com.example.habit.entity.Habit;
import com.example.habit.exception.ErrorCode;
import com.example.habit.mapper.HabitMapper;
import com.example.habit.service.HabitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author posase
 * @since 2023-02-14
 */
@Service
public class HabitServiceImpl extends ServiceImpl<HabitMapper, Habit> implements HabitService {

    @Override
    public List<Habit> getHabitList(Long userId) {
        return baseMapper.selectByUserId(userId);
    }

    @Override
    public void checkIn(String uid) {
        Habit habit = baseMapper.selectById(uid);
        Utils.iAssert(habit != null, ErrorCode.HABIT_NO_FOUND);

        Integer preTime = habit.getRecordTime();
    }
}
