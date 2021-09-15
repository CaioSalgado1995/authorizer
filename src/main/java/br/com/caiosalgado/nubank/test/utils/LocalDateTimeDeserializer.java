package br.com.caiosalgado.nubank.test.utils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeDeserializer implements JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        // removing the "Z" of timestamp
        return LocalDateTime.parse(json.getAsString().substring(0, json.getAsString().length() - 1),
                DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}
