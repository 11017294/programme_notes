package com.chen.nots_web.global.vo;

import lombok.Data;

/**
 *
 * @author：MaybeBin
 * @date: 2022-02-11 17:27
 */
@Data
public class BaseVO<T> {

    /**
     * 唯一UID
     */
    private String uid;

    /**
     * 关键字
     */
    private String keyword;

    /**
     * 当前页
     */
    private Long currentPage;

    /**
     * 页大小
     */
    private Long pageSize;
}
