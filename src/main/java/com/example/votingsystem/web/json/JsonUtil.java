package com.example.votingsystem.web.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;


public class JsonUtil {

    public static final ObjectMapper JSON_MAPPER = JacksonObjectMapper.getMapper();

    public static <T> List<T> readValues(String json, Class<T> clazz) {
        ObjectReader reader = JSON_MAPPER.readerFor(clazz);
        try {
            return reader.<T>readValues(json).readAll();
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid read array from JSON:\n'" + json + "'", e);
        }
    }

    public static <T> T readValue(String json, Class<T> clazz) {
        try {
            return JSON_MAPPER.readValue(json, clazz);
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid read from JSON:\n'" + json + "'", e);
        }
    }

    public static <T> String writeArray(T... array) {
        return writeValue(array);
    }

    public static <T> String writeValue(T obj) {
        try {
            return JSON_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Invalid write to JSON:\n'" + obj + "'", e);
        }
    }

    public static <T> String writeValue(T obj, ObjectWriter ow) {
        try {
            return ow.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Invalid write to JSON:\n'" + obj + "'", e);
        }
    }

    public static <T> String writeWithExtraProps(T obj, String extraName, Object extraValue) {
        return writeWithExtraProps(obj, Collections.singletonMap(extraName, extraValue));
    }

    public static <T> String writeWithExtraProps(T obj, Map<String, Object> extraProps) {
        try {
            Map<String, Object> map = JSON_MAPPER.convertValue(obj, new TypeReference<Map<String, Object>>() {});
            map.putAll(extraProps);
            return JSON_MAPPER.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Invalid write to JSON:\n'" + obj + "'", e);
        }
    }
}
