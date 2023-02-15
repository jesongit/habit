package com.example.habit.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.example.habit.entity.Note;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author posase
 * @since 2023-02-14
 */
public interface NoteMapper extends BaseMapper<Note> {

    List<Note> selectByUserId(@Param("userId") Long userId);
}




