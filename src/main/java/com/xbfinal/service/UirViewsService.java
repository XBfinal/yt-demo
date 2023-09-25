package com.xbfinal.service;

import com.alibaba.fastjson.JSON;
import com.xbfinal.entity.UirViews;
import com.xbfinal.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Title: UirViewsService
 * @Author 笑霸fianl
 * @Package com.xbfinal.service
 * @Date 2023/9/24 15:25
 * @描述:
 */
public class UirViewsService {



    public void findAllNum(HttpServletRequest req, HttpServletResponse resp){
        try(SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            List<UirViews> findAllNum = sqlSession.selectList("findAllNum");

            String jsonString = JSON.toJSONString(findAllNum);
            System.out.println(jsonString);
            resp.getWriter().write(jsonString);

            sqlSession.update("addOneApi", 2);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
