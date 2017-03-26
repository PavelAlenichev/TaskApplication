package berthold.taskapplication.serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import berthold.taskapplication.data.sending_data.DataForSend;

/**
 * Created by User on 26.03.2017.
 */

public class DataSerializer implements JsonSerializer<DataForSend> {


    @Override
    public JsonElement serialize(DataForSend src, Type typeOfSrc, JsonSerializationContext context) {

        JsonObject result = new JsonObject();

        result.add("Динамическая форма", context.serialize(src.getForm()));

        return result;
    }
}