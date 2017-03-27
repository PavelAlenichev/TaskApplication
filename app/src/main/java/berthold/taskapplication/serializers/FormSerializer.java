package berthold.taskapplication.serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import berthold.taskapplication.service.Definer;
import berthold.taskapplication.service.factories.Factory;
import berthold.taskapplication.service.MetaDataAdapter;
import berthold.taskapplication.data.sending_data.Form;

/**
 * Сериализация полей данных
 */
public class FormSerializer implements JsonSerializer<Form> {
    private Factory factory;

    public FormSerializer(Factory factory) {
        this.factory = factory;
    }

    /**
     * Сериализация полей данных для вставки в класс DataForSend и его последующей сериализации
     *
     * @param src
     * @param typeOfSrc
     * @param context
     * @return JsonElement c введенными пользовательскими данными
     */
    @Override
    public JsonElement serialize(Form src, Type typeOfSrc, JsonSerializationContext context) {

        JsonObject result = new JsonObject();

        result.addProperty(MetaDataAdapter.getFields().get(0).getName(), factory.getValues().get(0));
        result.addProperty(MetaDataAdapter.getFields().get(1).getName(), factory.getValues().get(1));
        result.addProperty(Definer.defineSpinnerParam(factory), factory.getValues().get(2));

        return result;
    }
}