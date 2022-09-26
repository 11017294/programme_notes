package com.chen.notes_web.service;

import com.chen.notes_web.entity.SysDictType;
import com.chen.notes_web.global.service.SuperService;
import com.chen.notes_web.vo.SysDictTypeVO;

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
