package com.chen.nots_web.controller;


import cn.hutool.core.util.StrUtil;
import com.chen.nots_web.service.SysDictTypeService;
import com.chen.nots_web.vo.ResultBase;
import com.chen.nots_web.vo.SysDictTypeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ResultBase getListByDictType(@ApiParam(name = "uid", value = "笔记UID") @RequestParam String dictType) {
        if (StrUtil.isBlank(dictType)) {
            return ResultBase.error("没有传入dictType参数");
        }
        return ResultBase.ok().data("dictTypelist", sysDictTypeService.getListByDictType(dictType));
    }

    @ApiOperation(value = "添加字典类型", notes = "添加字典类型")
    @PostMapping("/add")
    public ResultBase add(SysDictTypeVO sysDictTypeVO){
        return ResultBase.ok().data("id", sysDictTypeService.addSysDictType(sysDictTypeVO));
    }

    @ApiOperation(value = "按uid删除字典类型", notes = "按uid删除字典类型")
    @PostMapping("/delete")
    public ResultBase delete(@ApiParam(name = "uid", value = "笔记UID") @RequestBody String uid){
        if(StrUtil.isBlank(uid)){
            return ResultBase.error("修改失败，没有传入uid");
        }
        boolean note = sysDictTypeService.removeById(uid);
        if(note){
            return ResultBase.ok().data("id",uid);
        }
        return ResultBase.ok();
    }

    @ApiOperation(value = "按uid修改字典类型", notes = "按uid修改字典类型")
    @PostMapping("/update")
    public ResultBase update(SysDictTypeVO sysDictTypeVO){
        if(StrUtil.isNotBlank(sysDictTypeVO.getUid())){
            return ResultBase.ok().data("id", sysDictTypeService.editById(sysDictTypeVO));
        }
        return ResultBase.error("没有传入uid参数");
    }
}
