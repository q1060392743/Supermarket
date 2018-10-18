package com.iss.util;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SongChong created by 2018/10/12 0012 15:08
 */
public class JsonUtil {
    /**
     * 将方法和参数转换为json串
     *
     * @param method 方法
     * @param param  参数
     * @return java.lang.String json串
     * @author SongChong created by9:24 2018/10/15 0015
     */
    public static String toJson(String method, Object param) {
        Map<String, Object> hm = new HashMap();
        hm.put("method", method);
        hm.put("param", param);
        Gson g = new Gson();
        String json = g.toJson(hm);
        return json;

    }
}