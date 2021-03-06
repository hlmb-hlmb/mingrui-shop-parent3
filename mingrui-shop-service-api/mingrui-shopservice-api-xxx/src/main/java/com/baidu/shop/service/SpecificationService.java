package com.baidu.shop.service;

import com.alibaba.fastjson.JSONObject;
import com.baidu.shop.base.Result;
import com.baidu.shop.dto.SpecGroupDTO;
import com.baidu.shop.dto.SpecParamDTO;
import com.baidu.shop.entity.SpecGroupEntity;
import com.baidu.shop.entity.SpecParamEntity;
import com.baidu.shop.validate.group.MingruiOperation;
import com.google.gson.JsonObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName SpecificationService
 * @Description: TODO
 * @Author hlmb
 * @Date 2021/1/25
 * @Version V1.0
 **/
@Api(tags = "规格接口")
public interface SpecificationService {
    @ApiOperation(value = "通过条件查询规格组")
    @GetMapping(value = "/specgroup/getSpecGroupInfo")
    
    Result<List<SpecGroupEntity>> getSpecGroupInfo(SpecGroupDTO specGroupDTO);
    @ApiOperation(value = "新增")
    @PostMapping(value = "/specgroup/save")
    Result<JsonObject> saveSpecGroup(@Validated({MingruiOperation.Add.class}) @RequestBody SpecGroupDTO specGroupDTO);

    @ApiOperation(value = "修改")
    @PutMapping(value = "/specgroup/save")
    Result<JsonObject> updateSpecGroup(@Validated({MingruiOperation.Update.class}) @RequestBody SpecGroupDTO specGroupDTO);

    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/specgroup/delete/{id}")
    Result<JsonObject> deleteSpecGroup(@PathVariable Integer id);

    @ApiOperation(value = "通过条件查询规格参数")
    @GetMapping(value = "/specparam/getSpecParamInfo")
    Result<List<SpecParamEntity>> getSpecParamInfo(SpecParamDTO specParamDTO);

    @ApiOperation(value = "新增规格参数")
    @PostMapping(value = "/specparam/save")
    Result<JSONObject> saveSpecParam(@RequestBody SpecParamDTO specParamDTO);

    @ApiOperation(value = "修改规格参数")
    @PutMapping(value = "/specparam/save")
    Result<JSONObject> updateSpecParam(@RequestBody SpecParamDTO specParamDTO);

    @ApiOperation(value = "删除规格参数")
    @DeleteMapping(value = "/specparam/delete")
    Result<JSONObject> deleteSpecParam(Integer id);
}
