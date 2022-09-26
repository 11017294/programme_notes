package com.chen.notes_web.vo;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author：MaybeBin
 * @date: 2022-02-10 10:50
 */
@Data
public class ResultBase<T> {

    private Integer code;

    private String msg;

    private Map data = new HashMap();

    public static <T> ResultBase<T> ok() {
        ResultBase r = new ResultBase();
        r.msg = "成功";
        r.code = 0;
        return r;
    }

    public static <T> ResultBase<T> error(String message) {
        ResultBase r = new ResultBase();
        r.msg = message;
        r.code = 1;
        return r;
    }

    public ResultBase<T> data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

}
