package com.baidu.shop.service;

import com.baidu.shop.base.Result;
import com.baidu.shop.entity.CategoryEntity;
import com.baidu.shop.validate.group.MingruiOperation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "商品分类接口")
public interface CategoryService {
    @ApiOperation(value = "通过查询商品分类")
    @GetMapping(value = "category/list")
    Result<List<CategoryEntity>> getCategoryByPid(Integer pid);

    @ApiOperation(value = "删除")
    @DeleteMapping(value = "category/del")
    Result<Object>deleteById(Integer id);

    @ApiOperation(value = "修改")
    @PutMapping(value = "category/edit")
    Result<Object>editCategory(@Validated({MingruiOperation.Update.class}) @RequestBody CategoryEntity categoryEntity);

    @ApiOperation(value = "新增")
    @PostMapping(value = "category/save")
    Result<Object>saveCategory(@Validated({MingruiOperation.Add.class}) @RequestBody CategoryEntity categoryEntity);

    @ApiOperation(value = "根据id查询")
    @GetMapping(value = "category/brand")
    Result<List<CategoryEntity>> getCategoryByBrandId(Integer brandId);
}
