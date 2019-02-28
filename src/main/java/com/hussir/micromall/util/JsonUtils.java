package com.hussir.micromall.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import java.io.IOException;

public final class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setFilterProvider(new SimpleFilterProvider().setFailOnUnknownId(false));
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    }

    public static <T> String obj2String(T src) throws JsonProcessingException {

        if (src == null) {
            return null;
        }

        return src instanceof String ? (String) src : objectMapper.writeValueAsString(src);
    }

    @SuppressWarnings("unchecked")
    public static <T> T string2Obj(String src, TypeReference<T> typeReference) throws IOException {

        if (src == null) {
            return null;
        }

        return (typeReference.getType().equals(String.class) ? (T) src : objectMapper.readValue(src, typeReference));
    }
}
