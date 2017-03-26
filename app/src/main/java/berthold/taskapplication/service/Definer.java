package berthold.taskapplication.service;

import java.util.List;

import berthold.taskapplication.data.metadata.Field;

/**
 * Created by User on 25.03.2017.
 */

public class Definer {


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