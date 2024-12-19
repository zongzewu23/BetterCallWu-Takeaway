package com.zongzewu.bettercallwu.common;

import lombok.Data;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * After processing the returned results, the data from the server's response will eventually be converted into an object.
 * @param <T>
 */
@Data
public class R<T> {

    private Integer code; //1 for succeed 0 for any other fail result

    private String msg; //err message

    private T data;

    private Map map = new HashMap(); //dynamic data

    public static <T> R<T> success(T object) {
        R<T> r = new R<T>();
        r.data = object;
        r.code = 1;
        return r;
    }

    public static <T> R<T> error(String msg) {
        R r = new R();
        r.msg = msg;
        r.code = 0;
        return r;
    }

    public R<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

}
