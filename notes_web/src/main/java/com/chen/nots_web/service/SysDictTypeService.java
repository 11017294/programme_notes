package com.chen.nots_web.service;

import com.chen.nots_web.entity.SysDictType;
import com.chen.nots_web.global.service.SuperService;
import com.chen.nots_web.vo.SysDictTypeVO;

import java.util.List;

/**
 * <p>
 * 字典类型表 服务类
 * </p>
 *
 * @author MaybeBin
 * @since 2022-03-07
 */
public interface SysDictTypeService extends SuperService<SysDictType> {

    List<SysDictType> getListByDictType(String dictType);

    String addSysDictType(SysDictTypeVO sysDictTypeVO);

    String editById(SysDictTypeVO sysDictTypeVO);
}
