package com.example.habit.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.example.habit.common.Utils;
import com.example.habit.entity.Habit;
import com.example.habit.common.enums.ErrorCode;
import com.example.habit.entity.User;
import com.example.habit.mapper.HabitMapper;
import com.example.habit.mapper.UserMapper;
import com.example.habit.service.HabitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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

    @Autowired
    UserMapper userMapper;

    @Override
    public List<Habit> getHabitList(Long userId) {
        return baseMapper.selectByUserId(userId);
    }

    @Override
    public Habit checkIn(String uid) {
        Habit habit = baseMapper.selectById(uid);
        Utils.iAssert(habit != null, ErrorCode.HABIT_NO_FOUND);

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime record = habit.getRecordTime();
        LocalDateTime today = LocalDateTimeUtil.beginOfDay(now);
        Utils.iAssert(!LocalDateTimeUtil.isSameDay(record, today), ErrorCode.CHECK_IN_REPEAT);

        Long userId = habit.getUserId();
        Utils.iAssert(userId == StpUtil.getLoginIdAsLong(), ErrorCode.HABIT_VALID);
        User user = userMapper.selectById(userId);
        user.addPoint();

        int day = DateUtil.dayOfMonth(DateUtil.date());
        LocalDateTime yesterday = LocalDateTimeUtil.offset(today, -1, ChronoUnit.DAYS);
        LocalDateTime monDay = LocalDateTimeUtil.offset(today, -day + 1, ChronoUnit.DAYS);
        if(record == null) {
            // 首次打卡
            habit.check_in(day, false);
        } else {
            if(yesterday.isBefore(monDay))
                // 月份变了，重置
                habit.setRecord(0);
            habit.check_in(day, LocalDateTimeUtil.isSameDay(record, yesterday));
        }

        baseMapper.updateById(habit);
        userMapper.updateById(user);
        return habit;
    }
}
