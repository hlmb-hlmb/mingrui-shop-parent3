package com.baidu.shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baidu.shop.base.BaseApiService;
import com.baidu.shop.base.Result;
import com.baidu.shop.dto.SpecGroupDTO;
import com.baidu.shop.dto.SpecParamDTO;
import com.baidu.shop.entity.SpecGroupEntity;
import com.baidu.shop.entity.SpecParamEntity;
import com.baidu.shop.mapper.SpecGroupMapper;
import com.baidu.shop.mapper.SpecParamMapper;
import com.baidu.shop.service.SpecificationService;
import com.baidu.shop.utils.BaiduBeanUtil;
import com.baidu.shop.utils.ObjectUtil;
import com.google.gson.JsonObject;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName SpecificationServiceImpl
 * @Description: TODO
 * @Author hlmb
 * @Date 2021/1/25
 * @Version V1.0
 **/
@RestController
public class SpecificationServiceImpl extends BaseApiService implements SpecificationService {

    @Resource
    private SpecGroupMapper specGroupMapper;

    @Resource
    private SpecParamMapper specParamMapper;


    @Override
    public Result<List<SpecParamEntity>> getSpecParamInfo(SpecParamDTO specParamDTO) {
        SpecParamEntity specParamEntity = BaiduBeanUtil.copyProperties(specParamDTO,SpecParamEntity.class);
        Example example = new Example(SpecParamEntity.class);
        Example.Criteria criteria = example.createCriteria();

        if(ObjectUtil.isNotNull(specParamEntity.getGroupId()));
        criteria.andEqualTo("groupId",specParamEntity.getGroupId());

        if(ObjectUtil.isNotNull(specParamEntity.getCid()));
        criteria.andEqualTo("cid",specParamEntity.getCid());

        List<SpecParamEntity> list = specParamMapper.selectByExample(example);
        return this.setResultSuccess(list);
    }

    @Transactional
    @Override
    public Result<JSONObject> saveSpecParam(SpecParamDTO specParamDTO) {
        specParamMapper.insertSelective(BaiduBeanUtil.copyProperties(specParamDTO,SpecParamEntity.class));
        return this.setResultSuccess();
    }

    @Transactional
    @Override
    public Result<JSONObject> updateSpecParam(SpecParamDTO specParamDTO) {
        specParamMapper.updateByPrimaryKey(BaiduBeanUtil.copyProperties(specParamDTO,SpecParamEntity.class));
        return this.setResultSuccess();
    }

    @Transactional
    @Override
    public Result<JSONObject> deleteSpecParam(Integer id) {

        specParamMapper.deleteByPrimaryKey(id);
        return this.setResultSuccess();
    }
    @Override
    public Result<List<SpecGroupEntity>> getSpecGroupInfo(SpecGroupDTO specGroupDTO) {
        Example example = new Example(SpecGroupEntity.class);
        example.createCriteria().andEqualTo("cid", BaiduBeanUtil.copyProperties(specGroupDTO,SpecGroupEntity.class).getCid());
        List<SpecGroupEntity> list = specGroupMapper.selectByExample(example);
        return this.setResultSuccess(list);
    }

    @Override
    public Result<JsonObject> saveSpecGroup(SpecGroupDTO specGroupDTO) {
        specGroupMapper.insertSelective(BaiduBeanUtil.copyProperties(specGroupDTO,SpecGroupEntity.class));
        return this.setResultSuccess();
    }

    @Override
    public Result<JsonObject> updateSpecGroup(SpecGroupDTO specGroupDTO) {
        specGroupMapper.updateByPrimaryKeySelective(BaiduBeanUtil.copyProperties(specGroupDTO,SpecGroupEntity.class));
        return this.setResultSuccess();
    }

    @Override
    public Result<JsonObject> deleteSpecGroup(Integer id) {
        //判断规格组之前是否有规格参数
        Example example = new Example(SpecParamEntity.class);
        example.createCriteria().andEqualTo("groupId",id);

        List<SpecParamEntity> specParamEntities = specParamMapper.selectByExample(example);

        if(specParamEntities.size() > 0){
            return this.setResultError("不能删除此参数");
        }
        specGroupMapper.deleteByPrimaryKey(id);
        return this.setResultSuccess();
    }
}
