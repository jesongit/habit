package com.example.habit.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.example.habit.dto.NoteDto;
import com.example.habit.entity.Habit;
import com.example.habit.entity.Note;
import com.example.habit.entity.Timer;
import com.example.habit.service.HabitService;
import com.example.habit.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
@RequestMapping("/note")
public class NoteController {

    @Autowired
    NoteService noteService;

    @GetMapping("list")
    @Operation(summary = "笔记列表")
    @ApiResponse(description = "返回笔记列表")
    public SaResult list() {
        return SaResult.data(noteService.list());
    }

    @GetMapping("mylist")
    @Operation(summary = "个人笔记列表")
    @ApiResponse(description = "返回个人笔记列表")
    public SaResult myList() {
        Long userId = StpUtil.getLoginIdAsLong();
        return SaResult.data(noteService.getNoteList(userId));
    }

    @PostMapping(value = "new", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "发布笔记")
    @ApiResponse(description = "返回笔记信息")
    public SaResult publish(@RequestPart MultipartFile[] files,
                            @RequestPart String content) {
        Note note = noteService.publish(files, content);
        return SaResult.data(note);
    }

    @GetMapping("like")
    @Operation(summary = "点赞笔记")
    @ApiResponse(description = "返回点赞列表信息")
    public SaResult like(@RequestParam String uid) {
        Long userId = StpUtil.getLoginIdAsLong();
        Note note = noteService.like(userId, uid);
        return SaResult.data(note.getLikeList().length());
    }

    @PutMapping("put")
    @Operation(summary = "修改笔记")
    @ApiResponse(description = "返回笔记信息")
    public SaResult update(@RequestBody Note note) {
        noteService.updateById(note);
        return SaResult.data(note);
    }
}
