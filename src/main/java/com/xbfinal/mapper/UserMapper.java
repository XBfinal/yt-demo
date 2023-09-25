package com.xbfinal.mapper;

import com.xbfinal.entity.User;

/**
 * @Title: UserMapper
 * @Author 笑霸fianl
 * @Package com.xbfinal.mapper
 * @Date 2023/9/23 19:45
 * @描述:
 */
public interface UserMapper {

    /**
     * 根据id查用户
     * @param id
     * @return
     */
    User getUserById(int id) ;

    /**
     *  根据用户名查询用户
     * @param name
     * @return
     */
    User getUserByName(String name);

    /**
     * 保存用户
     * @param user
     * @return
     */
    boolean insertUser(User user);

}
