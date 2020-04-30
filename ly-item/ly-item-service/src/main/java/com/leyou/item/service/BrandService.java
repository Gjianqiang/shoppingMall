package com.leyou.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.enums.ExceptionEnums;
import com.leyou.common.exception.LyException;
import com.leyou.common.vo.PageResult;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.Brand;
import com.leyou.item.pojo.Category;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestParam;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Slf4j
public class BrandService {

    @Autowired
    private BrandMapper brandMapper;


    public PageResult<Brand> queryBrandByPage(Integer page, Integer rows, String sortBy, Boolean desc, String key) {
        //分页
        PageHelper.startPage(page,rows);//过滤器拦下来,拦下sql,再帮你分页
        //过滤
        Example example = new Example(Brand.class);
        if(StringUtils.isNotBlank(key)){
            //过滤条件
            brandMapper.selectByExample(example);
            //创建规则
            example.createCriteria().orLike("name", "%"+key+"%")
                    .orEqualTo("letter", key.toUpperCase());

        }
        //排序
        if(StringUtils.isNotBlank(sortBy)){
            String orderByClause=sortBy +(desc ? " desc":" ASC");
            example.setOrderByClause(orderByClause);
        }
        //查询
         List<Brand> brands = brandMapper.selectByExample(example);
         if(CollectionUtils.isEmpty(brands)){
             throw new LyException(ExceptionEnums.BRAND_NOT_FOUND);
         }
         //解析分页结果
        PageInfo<Brand> info=new PageInfo<>(brands);
        return new PageResult<Brand>(info.getTotal(),brands);
    }
    @Transactional//新增事务
    public void saveBrand(Brand brand,List<Long> cids){
        log.info("走进saveBrand");
        //新增品牌
        brand.setId((null));
        //跑完这个方法,brand的Id会回显
        int count = brandMapper.insert(brand);
        if(count!=1){
            throw new LyException(ExceptionEnums.BRAND_SAVE_ERROR);
        }
        //新增中间表
        for(Long cid:cids){
            int count1 = brandMapper.insertCategoryBrand(cid, brand.getId());
            if(count1 !=1){
                throw new LyException(ExceptionEnums.BRAND_SAVE_ERROR);
            }
        }
    }
    public Brand queryById(Long id){
        Brand brand = brandMapper.selectByPrimaryKey(id);
        if(brand==null){
            throw new LyException(ExceptionEnums.BRAND_NOT_FOUND);
        }
        return brand;
    }

    public List<Brand> queryBrandByCid(Long cid) {
        List<Brand> brands = brandMapper.queryBrandByCid(cid);
        if(CollectionUtils.isEmpty(brands)){
            throw new LyException(ExceptionEnums.BRAND_NOT_FOUND);
        }
        return brands;
    }
}

