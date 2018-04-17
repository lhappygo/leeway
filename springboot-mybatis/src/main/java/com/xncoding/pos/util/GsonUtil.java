package com.xncoding.pos.util;

import java.util.List;

import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GsonUtil {

    public static <T>T fromJson(String json,Class<T> type){
        if(StringUtils.isEmpty(json)){
            return null;
        }
        Gson gson = new Gson();
        return gson.fromJson(json,type);
    }
    public static <T> List<T> listFromJson(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<List<T>>(){}.getType());
    }
    
    public static <T> String toJsonStr(Object obj,Class<T> type){
        if(obj==null){
            return "";
        }
        Gson gson = new Gson();
        return gson.toJson(obj, type);
    }
    
}
