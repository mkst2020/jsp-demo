package com.gl.web.old;

import com.alibaba.fastjson.JSON;
import com.gl.pojo.Brand;
import com.gl.service.BrandService;
import com.gl.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
@WebServlet("/addBrandServlet")
public class AddBrandServlet extends HttpServlet {
    private BrandService brandService=new BrandServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String s = reader.readLine();
        Brand brand = JSON.parseObject(s, Brand.class);
        brandService.addBrand2(brand);
        resp.getWriter().write("success");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
