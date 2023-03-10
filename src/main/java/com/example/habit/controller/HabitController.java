package com.example.habit.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.hutool.core.util.StrUtil;
import com.example.habit.common.Utils;
import com.example.habit.entity.Habit;
import com.example.habit.service.HabitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author posase
 * @since 2023-02-14
 */
@Slf4j
@RestController
@RequestMapping("/habit")
public class HabitController {

    @Autowired
    HabitService habitService;

    @GetMapping("list")
    @Operation(summary = "获取习惯信息列表")
    @ApiResponse(description = "返回习惯信息列表")
    public SaResult list() {
        Long userId = StpUtil.getLoginIdAsLong();
        return SaResult.data(habitService.getHabitList(userId));
    }

    @PostMapping("new")
    @Operation(summary = "新建习惯")
    @ApiResponse(description = "返回习惯信息")
    public SaResult create(@RequestParam String name) {
        Long userId = StpUtil.getLoginIdAsLong();
        Habit habit = new Habit(userId, name);
        habitService.save(habit);
        return SaResult.data(habit);
    }

    @PutMapping("put")
    @Operation(summary = "修改习惯")
    @ApiResponse(description = "返回习惯信息")
    public SaResult update(@RequestBody Habit habit) {
        habitService.updateById(habit);
        return SaResult.data(habit);
    }

    @DeleteMapping("del")
    @Operation(summary = "删除习惯")
    @ApiResponse(description = "返回ok")
    public SaResult delete(@RequestParam String uid){
        habitService.removeById(uid);
        return SaResult.ok();
    }

    @GetMapping("checkin")
    @Operation(summary = "习惯打卡")
    @ApiResponse(description = "返回成功or失败")
    public SaResult checkIn(@RequestParam String uid) {
        return SaResult.data(habitService.checkIn(uid));
    }
}
