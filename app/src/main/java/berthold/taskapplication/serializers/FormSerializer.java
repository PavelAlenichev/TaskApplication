package berthold.taskapplication.serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import berthold.taskapplication.service.Definer;
import berthold.taskapplication.service.MetaDataAdapter;
import berthold.taskapplication.metadata.Form;

/**
 * Created by User on 26.03.2017.
 */

public class FormSerializer implements JsonSerializer<Form>{
    @Override
    public JsonElement serialize(Form src, Type typeOfSrc, JsonSerializationContext context) {

        JsonObject result = new JsonObject();

        result.addProperty(MetaDataAdapter.getFields().get(0).getName(), MetaDataAdapter.getValues().get(0));
        result.addProperty(MetaDataAdapter.getFields().get(1).getName(), MetaDataAdapter.getValues().get(1));
        result.addProperty(Definer.defineSpinnerParam(), MetaDataAdapter.getValues().get(2));

        return result;
    }
}
