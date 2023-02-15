package com.example.habit.service;

import com.example.habit.dto.NoteDto;
import com.example.habit.entity.Note;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author posase
 * @since 2023-02-14
 */
public interface NoteService extends IService<Note> {

    List<Note> getNoteList(Long userId);

    Note publish(MultipartFile[] files, String content);

    Note like(Long userId, String uid);
}
