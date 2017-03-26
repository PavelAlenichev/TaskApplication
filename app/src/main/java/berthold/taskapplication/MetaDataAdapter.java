package berthold.taskapplication;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import berthold.taskapplication.metadata.Field;
import berthold.taskapplication.service.TypesOfFields;

/**
 * Created by User on 24.03.2017.
 */

public class MetaDataAdapter extends RecyclerView.Adapter<MetaDataAdapter.ViewHolder> {

    private static List<Field> fields;
    private Context context;
    private EditText editNumeric;
    private EditText editText;
    private TextView postItem;
    private Spinner spinner;

    private static Double numValue;
    private static String textValue = "";
    private static String spinnerValue;

    public MetaDataAdapter(List<Field> fields, Context applicationContext) {
        this.fields = fields;
        this.context = applicationContext;
    }

    @Override
    public MetaDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.metadata_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MetaDataAdapter.ViewHolder holder, int position) {
        Field field = fields.get(position);

        postItem = new TextView(context);
        postItem.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        postItem.setTextColor(R.color.colorPrimary);
        postItem.setText(field.getTitle());

        GridLayout.Spec row = GridLayout.spec(position);
        GridLayout.Spec column = GridLayout.spec(0);
        GridLayout.LayoutParams gridLayoutParam = new GridLayout.LayoutParams(row, column);

        holder.metadataLayout.addView(postItem, gridLayoutParam);

        holder.metadataLayout.setPadding(10, 0, 10, 0);

        //TODO: FABRIC

        if (field.getType().equals(TypesOfFields.TEXT.toString())) {
            editText = new EditText(context);
            editText.setTextColor(Color.BLACK);
            editText.setText("");

            GridLayout.Spec columns = GridLayout.spec(1, GridLayout.CENTER);
            GridLayout.Spec rows = GridLayout.spec(position);

            GridLayout.LayoutParams numParams = new GridLayout.LayoutParams(rows, columns);
            editText.setMaxLines(1);
            numParams.width = RecyclerView.LayoutParams.MATCH_PARENT;


            holder.metadataLayout.addView(editText, numParams);

            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    textValue = s.toString();
                    if (s.toString().isEmpty()) {
                        textValue = "";
                    }
                }
            });


        } else if (field.getType().equals(TypesOfFields.NUMERIC.toString())) {
            editNumeric = new EditText(context);
            editNumeric.setInputType(2);
            editNumeric.setTextColor(Color.BLACK);
            editNumeric.setText("0.0");

            GridLayout.Spec columns = GridLayout.spec(1, GridLayout.CENTER);
            GridLayout.Spec rows = GridLayout.spec(position);

            GridLayout.LayoutParams numParams = new GridLayout.LayoutParams(rows, columns);
            editNumeric.setMaxLines(1);
            numParams.width = RecyclerView.LayoutParams.MATCH_PARENT;

            holder.metadataLayout.addView(editNumeric, numParams);

            editNumeric.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (!s.toString().isEmpty()) {
                        numValue = Double.parseDouble(s.toString());
                    } else {
                        numValue = 0.0;
                    }
                }
            });

        } else if (field.getType().equals(TypesOfFields.LIST.toString())) {
            spinner = new Spinner(context);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    context,
                    R.layout.spinner_item,
                    field.getValues().getAll());
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinner.setAdapter(adapter);
            spinner.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            GridLayout.Spec columns = GridLayout.spec(1, GridLayout.CENTER);
            GridLayout.Spec rows = GridLayout.spec(position);

            GridLayout.LayoutParams numParams = new GridLayout.LayoutParams(rows, columns);

            holder.metadataLayout.addView(spinner, numParams);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    spinnerValue = spinner.getSelectedItem().toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    spinnerValue = spinner.getSelectedItem().toString();
                }
            });
        }
    }

    public static List<String> getValues () {

        ArrayList<String> values = new ArrayList<>();

        values.add(textValue);
        try {
            values.add(numValue.toString());
        } catch (NullPointerException e) {
            values.add("0");
        }

        values.add(spinnerValue);

        return values;
    }

    public static List<Field> getFields() {
        return fields;
    }

    @Override
    public int getItemCount() {
        if (fields == null) {
            return 0;
        }
        return fields.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        GridLayout metadataLayout;


        public ViewHolder(View itemView) {
            super(itemView);
            metadataLayout = (GridLayout) itemView.findViewById(R.id.metadataLayout);
        }
    }
}