package berthold.taskapplication.service;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.List;

import berthold.taskapplication.R;
import berthold.taskapplication.data.metadata.Field;
import berthold.taskapplication.service.factories.Factory;

/**
 * Адаптер для RecyclerView с динамическим построением формы
 */

public class MetaDataAdapter extends RecyclerView.Adapter<MetaDataAdapter.ViewHolder> {

    private static List<Field> fields;
    private Context context;
    private TextView postItem;

    private static GridLayout metadataLayout;
    private Factory factory;


    public MetaDataAdapter(List<Field> fields, Factory factory, Context applicationContext) {
        this.fields = fields;
        this.context = applicationContext;
        this.factory = factory;
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

        factory.setViews(context, field, position, metadataLayout);
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