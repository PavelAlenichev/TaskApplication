package berthold.taskapplication;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridLayout;
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
    private EditText editNumeric;
    private EditText editText;
    private TextView postItem;
    private Spinner spinner;

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

        //holder.dataItem.setText(field.getTitle());

        //TODO: FABRIC

        if (field.getType().equals(TypesOfFields.TEXT.toString())) {
            editText = new EditText(context);
            editText.setTextColor(Color.BLACK);
            editText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            GridLayout.Spec columnnum = GridLayout.spec(1, GridLayout.CENTER);
            GridLayout.Spec rownum = GridLayout.spec(position);

            GridLayout.LayoutParams numParams = new GridLayout.LayoutParams(rownum, columnnum);
            editText.setMaxLines(1);
            numParams.width = RecyclerView.LayoutParams.MATCH_PARENT;


            holder.metadataLayout.addView(editText, numParams);


        } else if (field.getType().equals(TypesOfFields.NUMERIC.toString())) {
            editNumeric = new EditText(context);
            editNumeric.setInputType(2);
            editNumeric.setTextColor(Color.BLACK);

            GridLayout.Spec columnnum = GridLayout.spec(1, GridLayout.CENTER);
            GridLayout.Spec rownum = GridLayout.spec(position);

            GridLayout.LayoutParams numParams = new GridLayout.LayoutParams(rownum, columnnum);
            editNumeric.setMaxLines(1);
            numParams.width = RecyclerView.LayoutParams.MATCH_PARENT;

            holder.metadataLayout.addView(editNumeric, numParams);

        } else if (field.getType().equals(TypesOfFields.LIST.toString())) {
            spinner = new Spinner(context);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    context,
                    android.R.layout.simple_spinner_item,
                    field.getValues().getAll());
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            GridLayout.Spec columnnum = GridLayout.spec(1, GridLayout.CENTER);
            GridLayout.Spec rownum = GridLayout.spec(position);

            GridLayout.LayoutParams numParams = new GridLayout.LayoutParams(rownum, columnnum);

            holder.metadataLayout.addView(spinner, numParams);
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
        GridLayout metadataLayout;


        public ViewHolder(View itemView) {
            super(itemView);
            //dataItem = (TextView) itemView.findViewById(R.id.metadataPost);
            metadataLayout = (GridLayout) itemView.findViewById(R.id.metadataLayout);
        }
    }
}