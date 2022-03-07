package com.chen.nots_web.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chen.nots_web.entity.SysDictType;
import com.chen.nots_web.global.SQLConf;
import com.chen.nots_web.global.SysConf;
import com.chen.nots_web.global.service.serviceImpl.SuperServiceImpl;
import com.chen.nots_web.mapper.SysDictTypeMapper;
import com.chen.nots_web.service.SysDictTypeService;
import com.chen.nots_web.vo.SysDictTypeVO;
import com.github.xiaoymin.knife4j.core.util.StrUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 字典类型表 服务实现类
 * </p>
 *
 * @author MaybeBin
 * @since 2022-03-07
 */
@Service
public class SysDictTypeServiceImpl extends SuperServiceImpl<SysDictTypeMapper, SysDictType> implements SysDictTypeService {

    @Resource
    private SysDictTypeMapper sysDictTypeMapper;

    @Override
    public List<SysDictType> getListByDictType(String dictType) {
        return null;
    }

    @Override
    public String addSysDictType(SysDictTypeVO sysDictTypeVO) {
        if(StrUtil.isBlank(sysDictTypeVO.getDictType())){
            return null;
        }
        QueryWrapper<SysDictType> wrapper = new QueryWrapper();
        wrapper.eq(SQLConf.DICT_TYPE, sysDictTypeVO.getDictType());
        wrapper.last(SysConf.LIMIT_ONE);
        SysDictType temp = this.getOne(wrapper);
        if(temp != null){
            return null;
        }
        SysDictType sysDictType = new SysDictType();
        BeanUtil.copyProperties(sysDictTypeVO, sysDictType);
        sysDictType.insert();
        return sysDictType.getUid();
    }

    @Override
    public String editById(SysDictTypeVO sysDictTypeVO) {
        return "";
    }
}
