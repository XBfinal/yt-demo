package com.xbfinal.filter;



import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @Title: MyFillter
 * @Author 笑霸fianl
 * @Package com.xbfinal.fillter
 * @Date 2023/9/23 21:40
 * @描述:
 */
@WebFilter(urlPatterns = "/*" ,filterName = "MyFilter")
public class MyFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //设置中文编码
        servletResponse.setContentType("text/html;charset=UTF-8");
        servletResponse.setCharacterEncoding("utf-8");

        //放行
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
