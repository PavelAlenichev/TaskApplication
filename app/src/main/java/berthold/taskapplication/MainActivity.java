package berthold.taskapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import berthold.taskapplication.metadata.Field;
import berthold.taskapplication.metadata.MetaData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = "tags";
    private GridLayoutManager gridManager;

    RecyclerView recyclerView;
    List<Field> fields;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridManager = new GridLayoutManager(getApplicationContext(), 2);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);

        recyclerView = (RecyclerView) findViewById(R.id.data_recycle_view);
        recyclerView.setLayoutManager(manager);

        MetaDataAdapter adapter = new MetaDataAdapter(fields);
        recyclerView.setAdapter(adapter);

        TextView label = (TextView) findViewById(R.id.tableTitle);

        /*App.getApi()
                .getMetaData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.computation())
                .map(s -> fields = s.getFields())
                .subscribe();*/

        App.getApi().getMetaData().enqueue(new Callback<MetaData>() {
            @Override
            public void onResponse(Call<MetaData> call, Response<MetaData> response) {
                fields = response.body().getFields();
                MetaDataAdapter adapter1 = new MetaDataAdapter(response.body().getFields());
                recyclerView.setAdapter(adapter1);
                Log.d(LOG_TAG, response.body().getTitle());
                Log.d(LOG_TAG, response.body().getFields().get(2).getValues().getK1());
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MetaData> call, Throwable t) {
                Toast.makeText(MainActivity.this, "An error occurred during networking",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}