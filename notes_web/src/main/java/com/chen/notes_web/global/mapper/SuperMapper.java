package com.chen.notes_web.global.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * mapper 父接口，注意这个类不要让 mybatis-plus 扫描到！！
 *
 * @author：MaybeBin
 * @date: 2022-01-25 10:46
 */
public interface SuperMapper<T> extends BaseMapper<T> {
}
