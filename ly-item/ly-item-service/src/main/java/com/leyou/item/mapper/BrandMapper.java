package com.leyou.item.mapper;

import com.leyou.item.pojo.Brand;
import com.leyou.item.pojo.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BrandMapper extends Mapper<Brand> {
    //springMVC的注解写SQL语句
    @Insert("INSERT INTO TB_CATEGORY_BRAND (CATEGORY_ID,BRAND_ID) VALUES(#{cid},#{bid})")
    int insertCategoryBrand(@Param("cid")Long cid,@Param("bid") Long bid);
    @Select("SELECT b.* FROM tb_brand  b INNER JOIN tb_category_brand c on b.id=c.brand_id where c.category_id=#{cid}")
    List<Brand> queryBrandByCid(@Param("cid")Long cid);

}
