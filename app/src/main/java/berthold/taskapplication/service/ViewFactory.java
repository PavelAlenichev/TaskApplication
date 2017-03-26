package berthold.taskapplication.service;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Spinner;

import berthold.taskapplication.R;
import berthold.taskapplication.data.metadata.Field;

/**
 * Created by User on 26.03.2017.
 */

public class ViewFactory {

    private EditText editNumeric;
    private EditText editText;
    private Spinner spinner;

    public void setViews(Context context, Field field, int position, GridLayout metadataLayout) {
        if (field.getType().equals(TypesOfFields.TEXT.toString())) {

            GridLayout.LayoutParams numParams = configEditText(context, position);

            metadataLayout.addView(editText, numParams);

            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    MetaDataAdapter.setTextValue(s.toString());
                    if (s.toString().isEmpty()) {
                        MetaDataAdapter.setTextValue("");
                    }
                }
            });
        } else if (field.getType().equals(TypesOfFields.NUMERIC.toString())) {

            GridLayout.LayoutParams numParams = configEditNumeric(context, position);

            metadataLayout.addView(editNumeric, numParams);

            editNumeric.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    MetaDataAdapter.setNumValue(0.0);
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (!s.toString().isEmpty()) {
                        MetaDataAdapter.setNumValue(Double.parseDouble(s.toString()));
                    } else {
                        MetaDataAdapter.setNumValue(0.0);
                    }
                }
            });
        } else if (field.getType().equals(TypesOfFields.LIST.toString())) {

            GridLayout.LayoutParams numParams = configSpinner(context, field, position);

            metadataLayout.addView(spinner, numParams);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    MetaDataAdapter.setSpinnerValue(spinner.getSelectedItem().toString());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    MetaDataAdapter.setSpinnerValue(spinner.getSelectedItem().toString());
                }
            });
        }
    }

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

    @NonNull
    private GridLayout.LayoutParams configEditNumeric(Context context, int position) {
        editNumeric = new EditText(context);
        editNumeric.setMaxLines(1);
        editNumeric.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        editNumeric.setTextColor(Color.BLACK);

        GridLayout.Spec columns = GridLayout.spec(1, GridLayout.CENTER);
        GridLayout.Spec rows = GridLayout.spec(position);

        GridLayout.LayoutParams numParams = new GridLayout.LayoutParams(rows, columns);
        numParams.width = RecyclerView.LayoutParams.MATCH_PARENT;
        return numParams;
    }

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
}
