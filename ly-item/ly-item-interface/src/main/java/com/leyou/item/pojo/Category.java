package com.leyou.item.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name="tb_category")
@Data
/**
 * @author li
 * @time 2018/8/7
 * @feature: 商品分类对应的实体
 */
public class Category implements Serializable {
	@Id
	@KeySql(useGeneratedKeys = true)
	private Long id;
	private String name;
	private Long parentId;
	private Boolean isParent;
	/**
	 * 排序指数，越小越靠前
	 */
	private Integer sort;

	//get和set方法省略
	//注意isParent的set和get方法




}
