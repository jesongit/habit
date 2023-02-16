package com.example.habit.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.habit.common.Utils;
import com.example.habit.entity.Note;
import com.example.habit.common.enums.ErrorCode;
import com.example.habit.mapper.NoteMapper;
import com.example.habit.service.NoteService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
public class NoteServiceImpl extends ServiceImpl<NoteMapper, Note> implements NoteService{

    @Override
    public List<Note> getNoteList(Long userId) {
        return baseMapper.selectByUserId(userId);
    }

    @Override
    public Note publish(MultipartFile[] files, String content) {

        Utils.iAssert(!StrUtil.hasEmpty(content), ErrorCode.USERNAME);

        JSONArray images = JSONUtil.createArray();
        for (MultipartFile pFile : files) {
            File file = Utils.save_img(pFile);
            images.add(file.getName());
        }
        Long userId = StpUtil.getLoginIdAsLong();

        Note note = new Note(userId, images.toString(), content);
        baseMapper.insert(note);

        return note;
    }

    @Override
    public Note like(Long userId, String uid) {

        Note note = baseMapper.selectById(uid);
        Utils.iAssert(note != null, ErrorCode.NOTE_NO_FOUND);

        JSONArray likeList = JSONUtil.parseArray(note.getLikeList());
        Utils.iAssert(!userId.equals(note.getUserId()) && !likeList.contains(userId), ErrorCode.NOTE_LIKE_VALID);

        likeList.add(userId);
        note.setLikeList(likeList.toString());
        baseMapper.updateById(note);

        return note;
    }
}




