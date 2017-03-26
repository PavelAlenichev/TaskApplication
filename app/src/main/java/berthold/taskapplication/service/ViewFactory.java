package berthold.taskapplication.service;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Spinner;

import com.jakewharton.rxbinding2.widget.RxTextView;

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

            MetaDataAdapter.setTextValue("");
            RxTextView.textChangeEvents(editText)
                    .subscribe(e -> MetaDataAdapter.setTextValue(e.text().toString()));

        } else if (field.getType().equals(TypesOfFields.NUMERIC.toString())) {

            GridLayout.LayoutParams numParams = configEditNumeric(context, position);

            metadataLayout.addView(editNumeric, numParams);

            MetaDataAdapter.setNumValue(0.0);
            RxTextView.textChangeEvents(editNumeric)
                    .doOnNext(e -> {
                    if (e.text().toString().equals("")) MetaDataAdapter.setNumValue(0.0);})
                    .filter(e -> !e.text().toString().equals(""))
                    .subscribe(e -> MetaDataAdapter.setNumValue(Double.parseDouble(e.text().toString())));


        } else if (field.getType().equals(TypesOfFields.LIST.toString())) {

            GridLayout.LayoutParams numParams = configSpinner(context, field, position);

            metadataLayout.addView(spinner, numParams);

            //TODO: QUESTION ABOUT ANDROID RX LISTENER

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
}