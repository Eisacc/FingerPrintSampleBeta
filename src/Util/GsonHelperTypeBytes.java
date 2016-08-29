/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.Base64;

/**
 *
 * @author Administrador
 */
public class GsonHelperTypeBytes implements JsonSerializer<byte[]>, JsonDeserializer<byte[]> {

    @Override
    public JsonElement serialize(byte[] t, Type type, JsonSerializationContext jsc) {
        return new JsonPrimitive(new String(Base64.getEncoder().encode(t)));

    }

    @Override
    public byte[] deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {

        return Base64.getDecoder().decode(je.getAsString());
    }

}
