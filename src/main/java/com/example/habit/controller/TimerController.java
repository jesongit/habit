package com.example.habit.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.example.habit.entity.Timer;
import com.example.habit.service.TimerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 * 计时器表 前端控制器
 * </p>
 *
 * @author posase
 * @since 2023-02-14
 */
@RestController
@RequestMapping("/timer")
public class TimerController {

    @Autowired
    TimerService timerService;

    @GetMapping("list")
    @Operation(summary = "获取计时器记录")
    @ApiResponse(description = "返回记录列表")
    public SaResult list() {
        Long userId = StpUtil.getLoginIdAsLong();
        return SaResult.data(timerService.getTimerList(userId));
    }

    @PostMapping("add")
    @Operation(summary = "添加计时器记录")
    @ApiResponse(description = "返回计时器记录")
    public SaResult add(@RequestParam LocalDateTime startTime,
                        @RequestParam int duration) {
        Long userId = StpUtil.getLoginIdAsLong();
        Timer timer = new Timer(userId, startTime, duration);
        timerService.save(timer);
        return SaResult.data(timer);
    }
}
