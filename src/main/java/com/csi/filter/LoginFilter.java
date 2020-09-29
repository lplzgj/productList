package com.csi.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;

public class LoginFilter implements Filter {
    int start;
    int end;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
         start = Integer.parseInt(filterConfig.getInitParameter("start"));
         end = Integer.parseInt(filterConfig.getInitParameter("end"));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        int nowhour = now.getHours();
        if (nowhour>start&&nowhour<end){
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpSession session = request.getSession();
            Object admin = session.getAttribute("admin");
            if (admin==null){
                request.setAttribute("message","ÇëÏÈµÇÂ½£¬ÔÚ·ÃÎÊ");
                request.getRequestDispatcher("../login.jsp").forward(request,servletResponse);
            }
        }


        filterChain.doFilter(servletRequest,servletResponse);
    }

    public boolean getAddress(HttpServletRequest request){
        String path = request.getContextPath();
        String url = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
        System.out.println(url);
        String referer = request.getHeader("referer");
        System.out.println(referer);
        return referer!=null&&referer.startsWith(url);
    }

    @Override
    public void destroy() {

    }
}
