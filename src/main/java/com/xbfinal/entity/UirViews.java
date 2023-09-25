package com.xbfinal.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.Transient;
import java.io.Serializable;

/**
 * @Title: UirViews
 * @Author 笑霸fianl
 * @Package com.xbfinal.entity
 * @Date 2023/9/24 15:15
 * @描述:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UirViews  {
    /**
     * 用户id
     */
    @JSONField(serialize = false)
    private int id;

    /**
     * URL
     */
    private String uri;

    /**
     * 调用次数
     */
    private String viewNum;

}
