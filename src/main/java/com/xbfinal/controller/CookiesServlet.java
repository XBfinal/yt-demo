package com.xbfinal.controller;

import com.xbfinal.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Title: CookiesServlet
 * @Author 笑霸fianl
 * @Package com.xbfinal.controller
 * @Date 2023/9/24 14:59
 * @描述:
 */

@WebServlet(name = "CookiesServlet", urlPatterns = "/commService/cookies.do")
public class CookiesServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求中的所有Cookie
        Cookie[] cookies = req.getCookies();

        resp.setContentType("text/html");
        resp.getWriter().println("<html><head><title>Cookie信息</title></head><body>");

        if (cookies != null) {
            resp.getWriter().println("<h2>Cookie信息：</h2>");
            resp.getWriter().println("<ul>");

            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                String value = cookie.getValue();
                resp.getWriter().println("<li>" + name + " : " + value + "</li>");
            }

            resp.getWriter().println("</ul>");
        } else {
            resp.getWriter().println("<p>没有Cookie信息</p>");
        }

        //数据库加1
        try(SqlSession sqlSession = MybatisUtil.getSqlSession()){
            int addOneApi = sqlSession.update("addOneApi", 3);
            sqlSession.commit();
        }

        resp.getWriter().println("</body></html>");
    }
}
