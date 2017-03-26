package berthold.taskapplication;

import android.content.Context;
import android.widget.EditText;

import java.util.List;
import java.util.Objects;

import berthold.taskapplication.metadata.Field;
import berthold.taskapplication.service.TypesOfFields;

/**
 * Created by User on 25.03.2017.
 */

public class Definer {


    public static Object define(Field field, Context context) {
        Object neededField = new String("a");

        if (Objects.equals(field.getType(), TypesOfFields.TEXT.toString())) {
            neededField = (EditText) getEditText(context);
        }

        return neededField;
    }

    private static EditText getEditText(Context context) {
        EditText editText = new EditText(context);
        editText.setText("lol");


        return editText;
    }

    public static String lol() throws NoSuchFieldException {

        List<String> lol = MetaDataAdapter.getValues();

        List<Field> fields = MetaDataAdapter.getFields();


        String neededValue = lol.get(2);

        String param = "0";

        for (Field field : fields) {

            if (field.getType().equals(TypesOfFields.LIST.toString())) {
                Class c = field.getValues().getClass();

                java.lang.reflect.Field [] strings = c.getDeclaredFields();

                try {

                    for (java.lang.reflect.Field string : strings) {

                        string.setAccessible(true);

                        Object values = c.newInstance();

                        /*values.setEmpty(field.getValues().getEmpty());
                        values.setK1(field.getValues().getK1());
                        values.setK2(field.getValues().getK2());
                        values.setK3(field.getValues().getK3());
                        values.setK4(field.getValues().getK4());*/

                        String buffer = (String) string.get(values.getClass());

                        if (buffer.equals(neededValue)) {
                            param = buffer;
                        }

                    }
                } catch (Exception e) {
                    return e.getMessage();
                }
            }
        }

        return param;
    }
}