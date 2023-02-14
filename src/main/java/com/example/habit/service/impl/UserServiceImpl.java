package com.example.habit.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.example.habit.common.Utils;
import com.example.habit.dto.UserInfo;
import com.example.habit.dto.UserLogin;
import com.example.habit.entity.User;
import com.example.habit.exception.ErrorCode;
import com.example.habit.mapper.UserMapper;
import com.example.habit.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User register(UserInfo info) {

        String username = info.getUsername();

        List<User> res = baseMapper.selectByUsername(username);
        Utils.iAssert(res.size() == 0, ErrorCode.USERNAME);

        User user = new User();
        info.setPassword(Utils.encrypt(info.getPassword()));
        BeanUtils.copyProperties(info, user);
        System.out.println(user);
        baseMapper.insert(user);

        return user;
    }

    @Override
    public SaTokenInfo doLogin(UserLogin login) {

        String username = login.getUsername();
        String password = login.getPassword();

        List<User> list = baseMapper.selectByUsername(username);
        Utils.iAssert(list.size() > 0, ErrorCode.USERNAME);

        User user = list.get(0);
        String decrypt = Utils.decrypt(user.getPassword());
        Utils.iAssert(decrypt.equals(password), ErrorCode.PASSWORD);

        StpUtil.login(user.getId());

        return StpUtil.getTokenInfo();
    }

    @Override
    public void doLogout() {
        StpUtil.logout();
    }
}
