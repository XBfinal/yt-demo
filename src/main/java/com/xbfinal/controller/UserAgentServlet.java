package com.xbfinal.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Title: UserAgentServlet
 * @Author 笑霸fianl
 * @Package com.xbfinal.controller
 * @Date 2023/9/24 16:17
 * @描述:
 */
@WebServlet(name = "UserAgentServlet" ,urlPatterns = "/commService/userAgent.do")
public class UserAgentServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取User-Agent信息
        String userAgent = req.getHeader("User-Agent");

        // 设置响应内容类型为文本
        resp.setContentType("text/plain");
        resp.getWriter().println("User-Agent: " + userAgent);


    }
}
