package com.chen.notes_web.global.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.notes_web.global.mapper.SuperMapper;
import com.chen.notes_web.global.service.SuperService;

/**
 * SuperService 实现类（ 泛型：M 是  mapper(dao) 对象，T 是实体 ）
 *
 * @author：MaybeBin
 * @date: 2022-01-25 10:44
 */
public class SuperServiceImpl<M extends SuperMapper<T>, T> extends ServiceImpl<M, T> implements SuperService<T> {
}
