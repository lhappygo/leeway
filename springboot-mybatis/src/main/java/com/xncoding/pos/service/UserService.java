package com.xncoding.pos.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.xncoding.pos.common.dao.entity.User;
import com.xncoding.pos.common.dao.repository.UserMapper;
import com.xncoding.pos.util.GsonUtil;

/**
 * 后台用户管理
 */

@Service
public class UserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private RedisService redisService;

    @Resource
    private UserMapper userMapper;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 通过ID查找用户
     * 
     * @param id
     * @return
     */
    public User findById(Integer id) {
        return userMapper.selectById(id);
    }

    /**
     * 新增用户
     * 
     * @param user
     */
    public void insertUser(User user) {
        userMapper.insert(user);
    }

    /**
     * 修改用户
     * 
     * @param user
     */
    public void updateUser(User user) {
        userMapper.updateById(user);
    }

    /**
     * 删除用户
     * 
     * @param id
     */
    public void deleteUser(Integer id) {
        userMapper.deleteById(id);
    }

    public User getById(int id) {
        logger.info("获取用户start...");
        // 从缓存中获取用户信息
        String key = "user_" + id;
        User user = null;
        String cacheJson = redisService.get(key);
        if (StringUtils.isEmpty(cacheJson)) {
            user = userMapper.selectById(id);
            String json = GsonUtil.toJsonStr(user, User.class);
            redisService.set(key, json);
        } else {
            user = GsonUtil.fromJson(cacheJson, User.class);
        }
        return user;
    }
}
