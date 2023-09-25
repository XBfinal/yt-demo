package com.xbfinal.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Title: ImgObjServlet
 * @Author 笑霸fianl
 * @Package com.xbfinal.controller
 * @Date 2023/9/24 10:26
 * @描述: 验证码servlet
 */
@WebServlet(urlPatterns = "/imgObj")

public class ImgObjServlet  extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 自定义纯数字的验证码（随机4位数字，可重复）
        RandomGenerator randomGenerator = new RandomGenerator("0123456789", 4);
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(60, 30);
        lineCaptcha.setGenerator(randomGenerator);
        // 重新生成code
        lineCaptcha.createCode();

        //获取当前ip
        String clientIP  = req.getRemoteAddr();

        System.out.println("验证码："+lineCaptcha.getCode() +"\n ip==>"+clientIP);

        //存入session
        req.getSession().setAttribute(clientIP,lineCaptcha.getCode());

        try(ServletOutputStream outputStream = resp.getOutputStream()){
            lineCaptcha.write(outputStream);
       }



    }
}
