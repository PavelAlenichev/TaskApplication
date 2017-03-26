package berthold.taskapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import berthold.taskapplication.metadata.DataForSend;
import berthold.taskapplication.metadata.Field;
import berthold.taskapplication.metadata.Form;
import berthold.taskapplication.metadata.MetaData;
import berthold.taskapplication.serializers.DataSerializer;
import berthold.taskapplication.serializers.FormSerializer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = "tags";
    private RecyclerView.LayoutManager linearManager;

    private RecyclerView recyclerView;
    private List<Field> fields;

    private Button sendButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearManager = new LinearLayoutManager(getApplicationContext());

        recyclerView = (RecyclerView) findViewById(R.id.data_recycle_view);
        recyclerView.setLayoutManager(linearManager);

        MetaDataAdapter adapter = new MetaDataAdapter(fields, getApplicationContext());
        recyclerView.setAdapter(adapter);

        sendButton = (Button) findViewById(R.id.sendButton);


        /*App.getApi()
                .getMetaData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.computation())
                .map(s -> fields = s.getFields())
                .subscribe();*/

        final String[] title = new String[1];
        App.getApi().getMetaData().enqueue(new Callback<MetaData>() {
            @Override
            public void onResponse(Call<MetaData> call, Response<MetaData> response) {
                title[0] = response.body().toString();

                recyclerView.setVerticalScrollBarEnabled(true);
                recyclerView.setHorizontalScrollBarEnabled(true);

                fields = response.body().getFields();
                MetaDataAdapter adapter1 = new MetaDataAdapter(response.body().getFields(), getApplicationContext());
                recyclerView.setAdapter(adapter1);
                Log.d(LOG_TAG, title[0]);
                //Log.d(LOG_TAG, response.body().getFields().get(2).getValues().getK1());
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MetaData> call, Throwable t) {
                Toast.makeText(MainActivity.this, "An error occurred during networking",
                        Toast.LENGTH_SHORT).show();
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GsonBuilder builder = new GsonBuilder()
                        .setPrettyPrinting()
                        .registerTypeAdapter(Form.class, new FormSerializer())
                        .registerTypeAdapter(DataForSend.class, new DataSerializer())
                        .setLenient();
                Gson gson = builder.create();

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                AlertDialog dialog;
                alert.setTitle("Information");

                DataForSend dataForSend = new DataForSend();
                Form form = new Form();
                form.setTextEdit("");
                form.setNumEdit("");
                form.setSpinValue("");
                dataForSend.setForm(form);

                Log.d("Gson", gson.toJson(dataForSend));

                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                dialog = alert.create();
                dialog.show();
            }
        });
    }
}