package berthold.taskapplication;

import android.content.Context;
import android.widget.EditText;

import java.util.Objects;

import berthold.taskapplication.metadata.Field;
import berthold.taskapplication.service.TypesOfFields;

/**
 * Created by User on 25.03.2017.
 */

public class Definer {

    public static Object define (Field field, Context context) {
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
}
