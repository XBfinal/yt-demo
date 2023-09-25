package com.xbfinal.controller;

import com.xbfinal.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * @Title: UserIpServlet
 * @Author 笑霸fianl
 * @Package com.xbfinal.controller
 * @Date 2023/9/24 14:03
 * @描述: 显示用户ip
 */

@WebServlet(name = "UserIpServlet", urlPatterns = "/commService/userIp.do")
public class UserIpServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ip  = req.getRemoteAddr();

        //数据库加1
        try(SqlSession sqlSession = MybatisUtil.getSqlSession()){
            int addOneApi = sqlSession.update("addOneApi", 1);
            sqlSession.commit();
        }
        resp.getWriter().write(ip);
    }
}
