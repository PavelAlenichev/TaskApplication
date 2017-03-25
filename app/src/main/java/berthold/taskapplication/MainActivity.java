package berthold.taskapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import berthold.taskapplication.metadata.Field;
import berthold.taskapplication.metadata.MetaData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = "tags";
    private RecyclerView.LayoutManager linearManager;

    RecyclerView recyclerView;
    List<Field> fields;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearManager = new LinearLayoutManager(getApplicationContext());

        recyclerView = (RecyclerView) findViewById(R.id.data_recycle_view);
        recyclerView.setLayoutManager(linearManager);

        MetaDataAdapter adapter = new MetaDataAdapter(fields, getApplicationContext());
        recyclerView.setAdapter(adapter);



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
                String title = response.body().getTitle();

                recyclerView.setVerticalScrollBarEnabled(true);
                recyclerView.setHorizontalScrollBarEnabled(true);

                fields = response.body().getFields();
                MetaDataAdapter adapter1 = new MetaDataAdapter(response.body().getFields(), getApplicationContext());
                recyclerView.setAdapter(adapter1);
                Log.d(LOG_TAG, title);
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