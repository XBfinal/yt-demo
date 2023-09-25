package com.xbfinal.mapper;

import com.xbfinal.entity.UirViews;

/**
 * @Title: uirViewsMapper
 * @Author 笑霸fianl
 * @Package com.xbfinal.mapper
 * @Date 2023/9/24 15:12
 * @描述:
 */
public interface UirViewsMapper {

    /**
     * 返回所有数据
     * @return
     */
    UirViews findAllNum();

    void addOneApi(int id);

}
