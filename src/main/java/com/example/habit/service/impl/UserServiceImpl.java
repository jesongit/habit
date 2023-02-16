package com.example.habit.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.example.habit.common.Utils;
import com.example.habit.dto.UserDto;
import com.example.habit.dto.LoginDto;
import com.example.habit.entity.User;
import com.example.habit.common.enums.ErrorCode;
import com.example.habit.mapper.UserMapper;
import com.example.habit.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
    public User register(UserDto info) {

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
    public SaTokenInfo doLogin(LoginDto login) {

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

    @Override
    public User signIn(Long userId) {
        User user = baseMapper.selectById(userId);

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime time = user.getSignInTime();
        Utils.iAssert(!LocalDateTimeUtil.isSameDay(now, time), ErrorCode.SIGN_IN_REPEAT);

        LocalDateTime yesterday = LocalDateTimeUtil.offset(now, -1, ChronoUnit.DAYS);
        boolean serial = LocalDateTimeUtil.isSameDay(time, yesterday);
        user.signIn(serial);
        baseMapper.updateById(user);

        return user;
    }

    @Override
    public User covert(int count) {
        Long userId = StpUtil.getLoginIdAsLong();
        User user = baseMapper.selectById(userId);

        Long cost = count * 100L;
        Long myPoints = user.getPoints();
        Utils.iAssert(myPoints >= cost, ErrorCode.POINTS_LACK);

        user.delPoint(cost);
        user.setVipLevel(1);

        LocalDateTime time = user.getVipTime();
        LocalDateTime now = LocalDateTime.now();
        time = time != null && time.isAfter(now) ? time : now;
        user.setVipTime(LocalDateTimeUtil.offset(time, count, ChronoUnit.MONTHS));

        baseMapper.updateById(user);

        return user;
    }
}
