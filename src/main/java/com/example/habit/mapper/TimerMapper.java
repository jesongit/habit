package com.example.habit.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.example.habit.entity.Timer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 计时器表 Mapper 接口
 * </p>
 *
 * @author posase
 * @since 2023-02-14
 */
public interface TimerMapper extends BaseMapper<Timer> {

    List<Timer> selectByUserId(@Param("userId") Long userId);
}
