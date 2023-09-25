package com.xbfinal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: User
 * @Author 笑霸fianl
 * @Package com.xbfinal.entiy
 * @Date 2023/9/23 19:03
 * @描述: 用户实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    /**
     * 用户id
     */
    private int id;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户名
     */
    private String name;



}
