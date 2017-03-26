package berthold.taskapplication.service;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import berthold.taskapplication.R;
import berthold.taskapplication.data.metadata.Field;

/**
 * Адаптер для RecyclerView с динамическим построением формы
 */

public class MetaDataAdapter extends RecyclerView.Adapter<MetaDataAdapter.ViewHolder> {

    private static List<Field> fields;
    private Context context;
    private TextView postItem;

    private static GridLayout metadataLayout;

    public static GridLayout getMetadataLayout() {
        return metadataLayout;
    }

    private static Double numValue;
    private static String textValue = "";
    private static String spinnerValue;

    public static void setNumValue(Double numValue) {
        MetaDataAdapter.numValue = numValue;
    }

    public static void setTextValue(String textValue) {
        MetaDataAdapter.textValue = textValue;
    }

    public static void setSpinnerValue(String spinnerValue) {
        MetaDataAdapter.spinnerValue = spinnerValue;
    }

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

        metadataLayout.addView(postItem, gridLayoutParam);


        metadataLayout.setPadding(10, 0, 15, 0);


        ViewFactory factory = new ViewFactory();
        factory.setViews(context, field, position, metadataLayout);
    }

    public static List<String> getValues() {

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


        public ViewHolder(View itemView) {
            super(itemView);
            metadataLayout = (GridLayout) itemView.findViewById(R.id.metadataLayout);
        }
    }
}