package com.baidu.shop.service;

import com.baidu.shop.base.Result;
import com.baidu.shop.dto.BrandDTO;
import com.baidu.shop.entity.BrandEntity;
import com.baidu.shop.validate.group.MingruiOperation;
import com.github.pagehelper.PageInfo;
import com.google.gson.JsonObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @ClassName BrandService
 * @Description: TODO
 * @Author hlmb
 * @Date 2021/1/20
 * @Version V1.0
 **/
@Api(tags = "品牌接口")
public interface BrandService {
    @ApiOperation(value = "查询商品列表")
    @GetMapping(value = "brand/list")
    Result<PageInfo<BrandEntity>> getBrandInfo(BrandDTO brandDTO);

    @GetMapping(value = "brand/getBrandInfoByCategoryId")
    @ApiOperation(value = "通过id分类查询品牌")
    Result<List<BrandEntity>> getBrandInfoByCategoryId(Integer cid);

    @ApiOperation(value = "新增品牌")
    @PostMapping(value = "brand/save")
    Result<JsonObject> saveBrandInfo(@Validated({MingruiOperation.Add.class}) @RequestBody BrandDTO brandDTO);

    @ApiOperation(value = "修改品牌")
    @PutMapping(value = "brand/save")
    Result<JsonObject> editBrandInfo(@Validated({MingruiOperation.Update.class}) @RequestBody BrandDTO brandDTO);

}
