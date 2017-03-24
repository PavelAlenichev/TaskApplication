package berthold.taskapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import berthold.taskapplication.metadata.Field;

/**
 * Created by User on 24.03.2017.
 */

public class MetaDataAdapter extends RecyclerView.Adapter<MetaDataAdapter.ViewHolder> {

    private List<Field> fields;

    public MetaDataAdapter(List<Field> fields) {
        this.fields = fields;
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

        public ViewHolder(View itemView) {
            super(itemView);
            dataItem = (TextView) itemView.findViewById(R.id.metadataPost);
        }
    }
}