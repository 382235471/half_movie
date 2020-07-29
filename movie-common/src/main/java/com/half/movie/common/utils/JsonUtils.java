package com.half.movie.common.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> String obj2String(T src) {
        if (src == null) {
            return null;
        }
        try {
            return src instanceof String ? (String) src : objectMapper.writeValueAsString(src);
        } catch (Exception e) {
//            log.warn("parse object to String exception, error:{}", e);
            return "parse object to String exception";
        }
    }

    public static <T> T string2Obj(String src, TypeReference<T> typeReference) {
        if (src == null || typeReference == null) {
            return null;
        }
        try {
            return (T) (typeReference.getType().equals(String.class) ? src : objectMapper.readValue(src, typeReference));
        } catch (Exception e) {
//            log.warn("parse String to Object exception, String:{}, TypeReference<T>:{}, error:{}", src, typeReference.getType(), e);
            return null;
        }
    }
}
