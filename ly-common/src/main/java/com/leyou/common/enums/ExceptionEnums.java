package com.leyou.common.enums;
/**
 * 枚举，
 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor//使用后添加一个构造函数，该构造函数含有所有已声明字段属性参数
@NoArgsConstructor//使用后创建一个无参构造函数
public enum ExceptionEnums {
    //private finally static ExceptionEnums ex=new ExceptionEnums(400,"价格不能为空");
    PRICE_CANNOT_BE_NULL(400,"价格不能为空"),
    CATEGORY_NOT_FOUND(404,"商品分类没有查到"),
    BRAND_NOT_FOUND(404,"查询品牌没有找到"),
    BRAND_SAVE_ERROR(500,"新增品牌失败"),
    UPLOAD_FILE_ERROR(500,"文件上传失败"),
    INVALID_FILE_TYPE(400,"无效文件类型"),
    SPEC_GROUP_NOT_FOUND(404,"商品规格组没有找到"),
    GOODS_IS_NOT_FOUND(404,"商品没有找到"),
    GOODS_SAVE_ERROR(500,"商品新增失败"),
    GOODS_DETAIL_NOT_FOUND(404,"商品详情不存在"),
    GOODS_SKU_NOT_FOUND(404,"商品SKU不存在"),
    GOODS_STOCK_NOT_FOUND(404,"s商品库存不存在")
        ;//值必须在分号之前

    private int code;
     private String msg;
}
