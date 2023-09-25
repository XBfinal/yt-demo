package com.xbfinal.controller;

import com.xbfinal.service.UirViewsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Title: UirViewsServlet
 * @Author 笑霸fianl
 * @Package com.xbfinal.controller
 * @Date 2023/9/24 15:24
 * @描述:
 */
@WebServlet(name = "UirViewsServlet", urlPatterns = "/commService/uirViews.do")
public class UirViewsServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        UirViewsService uirViewsService = new UirViewsService();
        uirViewsService.findAllNum(req, resp);
    }
}
