package com.example.habit.service.impl;

import com.example.habit.entity.Chat;
import com.example.habit.mapper.ChatMapper;
import com.example.habit.service.ChatService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author posase
 * @since 2023-02-14
 */
@Service
public class ChatServiceImpl extends ServiceImpl<ChatMapper, Chat> implements ChatService {

}
