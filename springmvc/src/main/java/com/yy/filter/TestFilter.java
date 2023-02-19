package com.yy.filter;

import com.alibaba.fastjson.JSON;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: chen
 * @Date: 2023/2/13 19:21
 * @Desc: 帮朋友测试, 拦截器返回json
 */
public class TestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String uri = ((HttpServletRequest) servletRequest).getRequestURI();

        if (uri.contains("test")) {
            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
            httpServletResponse.setContentType("application/json;charset=UTF-8");

            try (PrintWriter writer = httpServletResponse.getWriter()) {
                writer.write(JSON.toJSONString(new Result(302, "/login")));
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        System.out.println(uri);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
