package berthold.taskapplication;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import berthold.taskapplication.metadata.Field;
import berthold.taskapplication.service.TypesOfFields;

/**
 * Created by User on 24.03.2017.
 */

public class MetaDataAdapter extends RecyclerView.Adapter<MetaDataAdapter.ViewHolder> {

    private List<Field> fields;
    private Context context;

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

        holder.dataItem.setText(field.getTitle());

        //TODO: FABRIC

        if (field.getType().equals(TypesOfFields.TEXT.toString())) {
            EditText editText = new EditText(context);
            editText.setTextColor(Color.BLACK);
            holder.metadataLayout.addView(editText);
        } else if (field.getType().equals(TypesOfFields.NUMERIC.toString())) {
            EditText editNumeric = new EditText(context);
            editNumeric.setInputType(3);
            editNumeric.setTextColor(Color.BLACK);
            holder.metadataLayout.addView(editNumeric);
        } else if (field.getType().equals(TypesOfFields.LIST.toString())) {
            Spinner spinner = new Spinner(context);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    context,
                    android.R.layout.simple_spinner_item,
                    field.getValues().getAll());
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            holder.metadataLayout.addView(spinner);
        }


    }

    @Override
    public int getItemCount() {
        if (fields == null) {
            return 0;
        }
        return fields.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView dataItem;
        LinearLayout metadataLayout;


        public ViewHolder(View itemView) {
            super(itemView);
            dataItem = (TextView) itemView.findViewById(R.id.metadataPost);
            metadataLayout = (LinearLayout) itemView.findViewById(R.id.metadataLayout);
        }
    }
}