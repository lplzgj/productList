package com.csi.controller;

import com.csi.domain.Goods;
import com.csi.service.GoodsService;
import com.csi.service.impl.GoodsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
@WebServlet(name = "GoodsServlet",urlPatterns = "/goods")
@MultipartConfig
public class GoodsServlet extends HttpServlet {
    private GoodsService gs = new GoodsServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String op = req.getParameter("op");
        if ("add".equals(op)){
            this.add(req,resp);
        }else if ("findAll".equals(op)){
            this.findAll(req,resp);
        }
    }

    private void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Goods> all = gs.findAll();
        req.setAttribute("all",all);
        req.getRequestDispatcher("showAllGoods.jsp").forward(req,resp);
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String name = req.getParameter("name");
        String price = req.getParameter("price");
        Part part = req.getPart("img");
        String oldName = part.getSubmittedFileName();
        String newName = "/"+
                UUID.randomUUID().toString().replace("-","").toUpperCase()+
                oldName.substring(oldName.lastIndexOf("."));
        String path = this.getServletContext().getRealPath("/upload");
        File f = new File(path);
        if (!f.exists()){
            f.mkdir();
        }
        part.write(path+newName);
        String s = gs.addGoods(new Goods(1,name,"/upload"+newName,Double.valueOf(price)));
        System.out.println(s);
        resp.sendRedirect("showAllGoods.jsp");
    }
}
