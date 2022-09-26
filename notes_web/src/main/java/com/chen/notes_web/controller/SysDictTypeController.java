package com.chen.notes_web.controller;


import cn.hutool.core.util.StrUtil;
import com.chen.notes_web.entity.SysDictType;
import com.chen.notes_web.exception.BusinessException;
import com.chen.notes_web.exception.ErrorCode;
import com.chen.notes_web.service.SysDictTypeService;
import com.chen.notes_web.vo.SysDictTypeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 字典类型表 前端控制器
 * </p>
 *
 * @author MaybeBin
 * @since 2022-03-07
 */
@Api(value = "系统字典类型表相关接口", tags = {"系统字典类型表相关接口"})
@RestController
@RequestMapping("/sysDictType")
public class SysDictTypeController {

    @Autowired
    private SysDictTypeService sysDictTypeService;

    @ApiOperation(value = "根据字典类型获取字典数据", notes = "根据字典类型获取字典数据")
    @PostMapping("/getListByDictType")
    public List<SysDictType> getListByDictType(@ApiParam(name = "uid", value = "笔记UID") @RequestParam String dictType) {
        if (StrUtil.isBlank(dictType)) {
            throw new BusinessException(ErrorCode.REQUEST_PARAMS_ERROR);
        }
        return sysDictTypeService.getListByDictType(dictType);
    }

    @ApiOperation(value = "添加字典类型", notes = "添加字典类型")
    @PostMapping("/add")
    public String add(SysDictTypeVO sysDictTypeVO){
        return sysDictTypeService.addSysDictType(sysDictTypeVO);
    }

    @ApiOperation(value = "按uid删除字典类型", notes = "按uid删除字典类型")
    @PostMapping("/delete")
    public String delete(@ApiParam(name = "uid", value = "笔记UID") @RequestBody String uid){
        if(StrUtil.isBlank(uid)){
            throw new BusinessException(ErrorCode.REQUEST_PARAMS_ERROR);
        }
        boolean note = sysDictTypeService.removeById(uid);
        if(!note){
            throw new BusinessException(ErrorCode.NOT_FOUND);
        }
        return uid;
    }

    @ApiOperation(value = "按uid修改字典类型", notes = "按uid修改字典类型")
    @PostMapping("/update")
    public String update(SysDictTypeVO sysDictTypeVO){
        if(StrUtil.isBlank(sysDictTypeVO.getUid())){
            throw new BusinessException(ErrorCode.REQUEST_PARAMS_ERROR);
        }
        return sysDictTypeService.editById(sysDictTypeVO);
    }
}
