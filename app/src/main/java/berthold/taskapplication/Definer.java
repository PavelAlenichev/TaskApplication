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

    public static String defineSpinnerParam() {

        List<String> lol = MetaDataAdapter.getValues();

        List<Field> fields = MetaDataAdapter.getFields();


        String neededValue = lol.get(2);

        String param = "0";

        for (Field field : fields) {

            if (field.getType().equals(TypesOfFields.LIST.toString())) {

                if (neededValue.equals(field.getValues().getEmpty())) {
                    param = "empty";
                } else if (neededValue.equals(field.getValues().getK1())) {
                    param = "k1";
                } else if (neededValue.equals(field.getValues().getK2())) {
                    param = "k2";
                } else if (neededValue.equals(field.getValues().getK3())) {
                    param = "k3";
                } else if (neededValue.equals(field.getValues().getK4())) {
                    param = "k4";
                }
            }
        }
        return param;
    }
}