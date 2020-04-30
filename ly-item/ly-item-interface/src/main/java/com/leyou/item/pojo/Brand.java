package com.leyou.item.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * @author li
 * @time 2018/8/7
 * @feature: 商品分类对应的实体
 */
@Table(name="tb_brand")
@Data
public class Brand implements Serializable {
	@Id
	@KeySql(useGeneratedKeys = true)
	private Long id;
	private String name;// 品牌名称
	private String image;// 品牌图片
	private Character letter;
	/**
	 * 排序指数，越小越靠前
	 */

	//get和set方法省略
	//注意isParent的set和get方法




}
