package org.daniellpk.model.misc;


import com.google.gson.*;
import org.daniellpk.Main;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class InheritedKeyTypeAdapter implements JsonSerializer<InheritedKey>, JsonDeserializer<InheritedKey> {
    @Override
    public JsonElement serialize(InheritedKey inheritedKey, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", inheritedKey.id);
        jsonObject.addProperty("classname", inheritedKey.classname);
        return jsonObject;
    }


    @Override
    public InheritedKey deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        int id = jsonObject.get("id").getAsInt();
        String classname = jsonObject.get("classname").getAsString();
        return new InheritedKey(id, classname);
    }
}