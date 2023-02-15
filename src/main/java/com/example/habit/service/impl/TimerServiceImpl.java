package com.example.habit.service.impl;

import com.example.habit.entity.Timer;
import com.example.habit.mapper.TimerMapper;
import com.example.habit.service.TimerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 计时器表 服务实现类
 * </p>
 *
 * @author posase
 * @since 2023-02-14
 */
@Service
public class TimerServiceImpl extends ServiceImpl<TimerMapper, Timer> implements TimerService {

    @Override
    public List<Timer> getTimerList(Long userId) {
        return baseMapper.selectByUserId(userId);
    }
}
