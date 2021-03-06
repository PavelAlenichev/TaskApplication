package berthold.taskapplication.service;

import java.util.List;

import berthold.taskapplication.data.metadata.Field;
import berthold.taskapplication.service.factories.Factory;

/**
 * Для определения имени свойства выпадающего списка
 */
public class Definer {


    /**
     * Определение названия свойства spinner'a
     *
     * @param factory для получения текущего значения spinner'a
     * @return название свойства спиннера для текущего item'a
     */
    public static String defineSpinnerParam(Factory factory) {

        List<String> lol = factory.getValues();
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