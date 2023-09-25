package com.xbfinal.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Title: SecureFilter
 * @Author 笑霸fianl
 * @Package com.xbfinal.filter
 * @Date 2023/9/24 14:34
 * @描述:
 */

@WebFilter(urlPatterns = "/*", filterName = "SecureFilter")
public class SecureFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 获取请求的URL

        HttpServletRequest request =   (HttpServletRequest) servletRequest;
        String requestURI =request.getRequestURI();

        // 检查URL是否以"do"结尾
        if (requestURI.endsWith("do")) {
            // 如果是以"do"结尾的请求，执行所需的操作
            //判断是否登录

            Object isUser = request.getSession().getAttribute("isUser");

            if(isUser!=null){
                filterChain.doFilter(servletRequest, servletResponse);
            }else {
                request.getRequestDispatcher("/view/index.jsp").forward(servletRequest, servletResponse);
            }



        } else {
            // 如果不是以"do"结尾的请求放行
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
