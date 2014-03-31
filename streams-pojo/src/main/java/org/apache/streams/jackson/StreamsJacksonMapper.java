package org.apache.streams.jackson;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;

/**
 * Created by sblackmon on 3/27/14.
 */
public class StreamsJacksonMapper extends ObjectMapper {

    public static final DateTimeFormatter ACTIVITY_FORMAT = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

    private static final StreamsJacksonMapper INSTANCE = new StreamsJacksonMapper();

    public static StreamsJacksonMapper getInstance(){
        return INSTANCE;
    }

    public StreamsJacksonMapper() {
        super();
        registerModule(new StreamsJacksonModule());
        disable(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, Boolean.TRUE);
        configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, Boolean.TRUE);
        configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, Boolean.TRUE);
        configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, Boolean.TRUE);
        configure(DeserializationFeature.WRAP_EXCEPTIONS, Boolean.FALSE);
        configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, Boolean.TRUE);
        setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

}
