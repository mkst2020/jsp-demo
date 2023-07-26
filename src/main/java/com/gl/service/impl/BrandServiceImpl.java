package com.gl.service.impl;

import com.gl.mapper.BrandMapper;
import com.gl.pojo.Brand;
import com.gl.pojo.PageBean;
import com.gl.service.BrandService;
import com.gl.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandServiceImpl implements BrandService {
    //获取SqlSessionFactory对象
   SqlSessionFactory sqlSessionFactory= SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public void deleteByIds(int[] ids) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.deleteByIds(ids);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public List<Brand> selectAll2() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = mapper.selectAll();
        sqlSession.close();
        return brands;
    }
    @Override
    public void addBrand2(Brand brand){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.addBrand(brand);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        int begin=(currentPage-1)*pageSize;
        int size=pageSize;
        String brandName = brand.getBrandName();
        if(brandName!=null && brandName.length()>0){
            brand.setBrandName("%"+brandName+"%");
        }
        String companyName = brand.getCompanyName();

        if(companyName!=null && companyName.length()>0){
            brand.setCompanyName("%"+companyName+"%");
        }


        int totalCount = mapper.selectTotalCountByCondition(brand);
        List<Brand> brands = mapper.selectByPageAndCondition(begin, size, brand);

        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setDatas(brands);
        pageBean.setTotalCount(totalCount);
        sqlSession.close();
        return pageBean;

    }

    @Override
    public PageBean <Brand>selectByPage(int currentPage, int size) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = mapper.selectByPage((currentPage - 1) * size, size);
        int totalCount = mapper.totalCount();

        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setDatas(brands);
        pageBean.setTotalCount(totalCount);
        sqlSession.close();
        return pageBean;
    }
}
