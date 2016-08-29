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
import java.util.Date;

/*
 *
 * @author Administrador
 */
public class GsonHelperTypeDate implements JsonSerializer<Date>, JsonDeserializer<Date> {

    /**
     *
     * @param t
     * @param type
     * @param jsc
     * @return
     */
    @Override
    public JsonElement serialize(Date t, Type type, JsonSerializationContext jsc) {
        return t == null ? null : new JsonPrimitive(t.getTime());
    }

    /**
     *
     * @param je
     * @param type
     * @param jdc
     * @return
     * @throws JsonParseException
     */
    @Override
    public Date deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        return je == null ? null : new Date(je.getAsLong());
    }
    
}
