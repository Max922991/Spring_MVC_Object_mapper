package org.example.spring_mvc_object_mapper.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import java.util.Optional;

/**
 * Не смог найти ТЗ к проекту, поэтому просто написал логику сер-ии и дес-ии!
 */

@Slf4j
public class Jsonmapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String serialize(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Error serializing object to JSON", e);
            throw new RuntimeException("Error serializing object to JSON", e);
        }
    }

    public static <T> Optional<T> deserialize(String json, Class<T> clazz) {
        try {
            return Optional.of(objectMapper.readValue(json, clazz));
        } catch (JsonProcessingException e) {
            log.error("Error deserializing JSON to object", e);
            return Optional.empty();
        }
    }
}
