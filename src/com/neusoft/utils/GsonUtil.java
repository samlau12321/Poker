package com.neusoft.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.List;

public class GsonUtil {
    public static String toJson(Object object) {
        GsonBuilder gb = new GsonBuilder();
        gb.setPrettyPrinting();
        Gson gson = gb.create();
        String str = gson.toJson(object);
        str = str.replace("\n", "");
        str = str.replace(" ", "");
        return str;
    }

    public static Object toObj(String str, Class<?> C) {
        JsonParser jp = new JsonParser();
        JsonArray jar = jp.parse(str).getAsJsonArray();
        Gson gson = new Gson();
        Object obj = null;
        for (JsonElement item : jar) {
            obj = gson.fromJson(item, C);
        }
        return obj;
    }

    public static List<Object> toObjList(String str, Class<?> C) {
        JsonParser jp = new JsonParser();
        JsonArray jar = jp.parse(str).getAsJsonArray();
        Gson gson = new Gson();
        List<Object> objList = new ArrayList<Object>();
        for (JsonElement item : jar) {
            Object obj = gson.fromJson(item, C);
            objList.add(obj);
        }
        return objList;
    }
}
