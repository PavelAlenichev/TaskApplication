package berthold.taskapplication.service.factories;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import berthold.taskapplication.R;
import berthold.taskapplication.data.metadata.Field;
import berthold.taskapplication.service.TypesOfFields;

/**
 * Factory для создания view и отображения на RecyclerView
 */
public class ViewFactory implements Factory {

    private static EditText editNumeric;
    private static EditText editText;
    private static Spinner spinner;

    /**
     * Создание views в зависимости от типа
     *
     * @param context
     * @param field
     * @param position
     * @param metadataLayout
     */
    public void setViews(Context context, Field field, int position, GridLayout metadataLayout) {
        if (field.getType().equals(TypesOfFields.TEXT.toString())) {

            GridLayout.LayoutParams numParams = configEditText(context, position);
            metadataLayout.addView(editText, numParams);

        } else if (field.getType().equals(TypesOfFields.NUMERIC.toString())) {

            GridLayout.LayoutParams numParams = configEditNumeric(context, position);
            metadataLayout.addView(editNumeric, numParams);

        } else if (field.getType().equals(TypesOfFields.LIST.toString())) {

            GridLayout.LayoutParams numParams = configSpinner(context, field, position);

            metadataLayout.addView(spinner, numParams);
        }
    }

    /**
     * Получение данных со всех полей в нужный момент
     *
     * @return List<String>, где
     * 0 - значение editText
     * 1 - значение editNumeric
     * 2 - значение spinner
     */
    @Override
    public List<String> getValues() {
        ArrayList<String> values = new ArrayList<>();

        values.add(editText.getText().toString());

        if (editNumeric.getText().toString().equals("")) {
            values.add("0.0");
        } else {
            values.add(editNumeric.getText().toString());
        }
        values.add(spinner.getSelectedItem().toString());

        return values;
    }


    /**
     * Конфигурация поля текстового ввода
     *
     * @param context
     * @param position
     * @return параметры размещения на layout
     */
    @NonNull
    private GridLayout.LayoutParams configEditText(Context context, int position) {
        editText = new EditText(context);
        editText.setTextColor(Color.BLACK);
        editText.setText("");


        GridLayout.Spec columns = GridLayout.spec(1, GridLayout.LEFT);
        GridLayout.Spec rows = GridLayout.spec(position);

        GridLayout.LayoutParams numParams = new GridLayout.LayoutParams(rows, columns);
        editText.setMaxLines(1);
        numParams.width = RecyclerView.LayoutParams.MATCH_PARENT;
        return numParams;
    }

    /**
     * Конфигурация поля численного ввода
     *
     * @param context
     * @param position
     * @return параметры размещения на layout
     */
    @NonNull
    private GridLayout.LayoutParams configEditNumeric(Context context, int position) {
        editNumeric = new EditText(context);
        editNumeric.setMaxLines(1);
        editNumeric.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        editNumeric.setTextColor(Color.BLACK);
        //editNumeric.setText("0");

        GridLayout.Spec columns = GridLayout.spec(1, GridLayout.CENTER);
        GridLayout.Spec rows = GridLayout.spec(position);

        GridLayout.LayoutParams numParams = new GridLayout.LayoutParams(rows, columns);
        numParams.width = RecyclerView.LayoutParams.MATCH_PARENT;
        return numParams;
    }

    /**
     * Конфигурация выпадающего списка
     *
     * @param context
     * @param position
     * @return параметры размещения на layout
     */
    @NonNull
    private GridLayout.LayoutParams configSpinner(Context context, Field field, int position) {
        spinner = new Spinner(context);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                context,
                R.layout.spinner_item,
                field.getValues().getAll());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setTextAlignment(View.FOCUS_LEFT);


        GridLayout.Spec columns = GridLayout.spec(1, GridLayout.LEFT);
        GridLayout.Spec rows = GridLayout.spec(position);

        return new GridLayout.LayoutParams(rows, columns);
    }

    public static int getSpinnerOrderItem() {
        return spinner.getSelectedItemPosition();
    }

    public static void setTextValue(String text) {
        editText.setText(text);
    }

    public static void setNumValue(String num) {
        editNumeric.setText(num);
    }

    public static void setSpinnerValue(int value) {
        spinner.setSelection(value);
    }
}