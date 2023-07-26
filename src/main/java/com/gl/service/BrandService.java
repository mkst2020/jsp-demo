package com.gl.service;

import com.gl.pojo.Brand;
import com.gl.pojo.PageBean;

import java.util.List;

public interface BrandService {
    public List<Brand>selectAll2();
    public void addBrand2(Brand brand);
    public void deleteByIds(int[] ids);
    public PageBean<Brand> selectByPage(int currentPage,int size);
    public PageBean<Brand> selectByPageAndCondition(int currentPage,int pageSize,Brand brand);

}
