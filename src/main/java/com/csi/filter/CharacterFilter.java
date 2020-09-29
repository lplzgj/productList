package com.csi.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;
@WebFilter(filterName = "CharacterFilter",urlPatterns = "/*",initParams = {
        @WebInitParam(name = "character",value = "utf-8")
})
public class CharacterFilter implements Filter {
    private String character;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始方法");
        this.character=filterConfig.getInitParameter("character");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("过滤器过滤方法");
        servletRequest.setCharacterEncoding(this.character);
        servletResponse.setCharacterEncoding(this.character);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("过滤器销毁方法");
    }
}
