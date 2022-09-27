package com.chen.notes_web.export;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * <p>
 *  导出策略，service实现接口的两个方法即可
 *  MyExport会自动调用
 * </p>
 *
 * @author：MaybeBin
 * @Date: 2022-09-27 08-50
 */
public interface ExportBaseService<T> {

    Integer totalExport();

    /**
     * 查询条件必须带上导出时间
     * createTime 小于导出时间
     */
    List<T> exportQuery(IPage<T> page);
}
