package com.xbfinal.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.xbfinal.entity.User;
import com.xbfinal.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Title: UserService
 * @Author 笑霸fianl
 * @Package com.xbfinal.service
 * @Date 2023/9/23 21:06
 * @描述:
 */
public class UserService {

    /**
     * 登录
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */

    public void login(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        boolean flag = false;
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        if (name == null || " ".equals(name)) {
            request.setAttribute("msg", "登录失败请检查用户名");
            request.getRequestDispatcher("/view/index.jsp").forward(request, response);
            return;
        }

        //判断输入密码
        if (password == null ||  " ".equals(password)) {
            request.setAttribute("msg", "请输入密码");
            request.getRequestDispatcher("/view/index.jsp").forward(request, response);
            return;
        }
        //判断图片验证码 HttpServletRequest
        String verificationCode = req.getParameter("verificationCode");
        String attribute = (String) request.getSession().getAttribute(req.getRemoteAddr());
        if (!verificationCode.equals(attribute)) {
            request.setAttribute("msg", "登录失败请检查验证码");
            request.getRequestDispatcher( "/view/index.jsp").forward(request, response);
            return;
        }

        //查数据库
        User user;
        try(SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            user = sqlSession.selectOne("getUserByName", name);
        }

        if (BeanUtil.isEmpty(user)) {
            request.setAttribute("msg", "登录失败请检查用户名");
            request.getRequestDispatcher( "/view/index.jsp").forward(request, response);
            return;
        }
        String password2 = SecureUtil.md5(password);

        if (password2.equals(user.getPassword())) {
            flag = true;
        }

        if (flag) {
            //转发到新页面
            request.setAttribute("msg", "");
            request.getSession().setAttribute("isUser",user.getId());
            request.getRequestDispatcher("/view/page1.jsp").forward(request, response);
            return;
        }

        request.getRequestDispatcher( "/view/index.jsp").forward(request, response);


    }


    public void   register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        if(StrUtil.isEmpty(name) ||StrUtil.isEmpty(password) ){
            req.setAttribute("msg", "用户名和密码不能为空");
            req.getRequestDispatcher( "/view/register.jsp").forward(req, resp);
            return ;
        }
        //加密 密码
        String md5 = SecureUtil.md5(password);
        User newUser = new User();
        newUser.setName(name);
        newUser.setPassword(md5);

        //查询数据库防止重名
        try(SqlSession sqlSession = MybatisUtil.getSqlSession()) {
           User user = sqlSession.selectOne("getUserByName", name);
           if(BeanUtil.isNotEmpty(user)){
               req.setAttribute("msg", "用户名已经存在");
               req.getRequestDispatcher( "/view/register.jsp").forward(req, resp);
               return ;
           }

           //存入数据库
            sqlSession.insert("insertUser", newUser);
            sqlSession.commit();
        }
        //跳转登录页面
        req.getRequestDispatcher( "/view/index.jsp").forward(req, resp);



    }
}
