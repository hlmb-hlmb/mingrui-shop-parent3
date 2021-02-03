package com.baidu.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

/**
 * @ClassName CategoryBrandEntity
 * @Description: TODO
 * @Author hlmb
 * @Date 2021/1/19
 * @Version V1.0
 **/
@Table(name = "tb_category_brand")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryBrandEntity {

    private Integer categoryId;

    private  Integer brandId;

}

