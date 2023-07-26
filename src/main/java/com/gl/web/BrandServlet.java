package com.gl.web;

import com.alibaba.fastjson.JSON;
import com.gl.pojo.Brand;
import com.gl.pojo.PageBean;
import com.gl.service.BrandService;
import com.gl.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BrandServlet", value = "/brand/*")
public class BrandServlet extends BaseServlet {
    private BrandService brandService=new BrandServiceImpl();
    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        List<Brand> brands = brandService.selectAll2();

        resp.setContentType("text/json;charset=utf-8");
        String jsonString = JSON.toJSONString(brands);
        resp.getWriter().write(jsonString);
    }
    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        BufferedReader reader = req.getReader();
        String s = reader.readLine();
        Brand brand = JSON.parseObject(s, Brand.class);
        brandService.addBrand2(brand);
        resp.getWriter().write("success");
    }
    public void deleteByIds(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        BufferedReader reader = req.getReader();
        String s = reader.readLine();
        int[] ids = JSON.parseObject(s, int[].class);
        brandService.deleteByIds(ids);
        resp.getWriter().write("success");
    }
    public void selectByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        String currentPage = req.getParameter("currentPage");
        String pageSize = req.getParameter("pageSize");
        int _currentPage = Integer.parseInt(currentPage);
        int _pageSize = Integer.parseInt(pageSize);
        PageBean<Brand> brandPageBean = brandService.selectByPage(_currentPage, _pageSize);

        resp.setContentType("text/json;charset=utf-8");
        String jsonString = JSON.toJSONString(brandPageBean);
        resp.getWriter().write(jsonString);
    }

public void selectByPageAndCondition(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        String currentPage = req.getParameter("currentPage");
        String pageSize = req.getParameter("pageSize");
        int _currentPage = Integer.parseInt(currentPage);
        int _pageSize = Integer.parseInt(pageSize);
    BufferedReader reader = req.getReader();
    String params = reader.readLine();
    Brand brand = JSON.parseObject(params, Brand.class);

    PageBean<Brand> brandPageBean = brandService.selectByPageAndCondition(_currentPage, _pageSize, brand);

    resp.setContentType("text/json;charset=utf-8");
        String jsonString = JSON.toJSONString(brandPageBean);
        resp.getWriter().write(jsonString);
    }

}
