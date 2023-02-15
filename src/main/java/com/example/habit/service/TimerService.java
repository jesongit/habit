package com.example.habit.service;

import com.example.habit.entity.Timer;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 计时器表 服务类
 * </p>
 *
 * @author posase
 * @since 2023-02-14
 */
public interface TimerService extends IService<Timer> {

    List<Timer> getTimerList(Long userId);
}
