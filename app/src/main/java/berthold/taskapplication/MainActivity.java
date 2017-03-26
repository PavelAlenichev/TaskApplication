package berthold.taskapplication;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import berthold.taskapplication.data.metadata.Field;
import berthold.taskapplication.data.sending_data.DataForSend;
import berthold.taskapplication.data.sending_data.Form;
import berthold.taskapplication.serializers.DataSerializer;
import berthold.taskapplication.serializers.FormSerializer;
import berthold.taskapplication.service.App;
import berthold.taskapplication.service.DataForSendBouilder;
import berthold.taskapplication.service.MetaDataAdapter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = "tags";
    private RecyclerView.LayoutManager linearManager;

    private RecyclerView recyclerView;
    private List<Field> fields;

    private Button sendButton;

    private ProgressDialog progressDialog;
    private TextView tableTitle;

    Disposable metaDataCall;
    Disposable queryCall;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configRecyclerView();

        sendButton = (Button) findViewById(R.id.sendButton);

        configProgressDialog();


        /*App.getApi()
                .getMetaData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.computation())
                .map(s -> fields = s.getFields())
                .subscribe();

                _______________to response:

                progressDialog.dismiss();
                Log.d(LOG_TAG, response.body().getTitle());
                setTitle(response.body().getTitle());

                recyclerView.setVerticalScrollBarEnabled(true);
                recyclerView.setHorizontalScrollBarEnabled(true);

                fields = response.body().getFields();
                MetaDataAdapter adapter1 = new MetaDataAdapter(response.body().getFields(), getApplicationContext());
                recyclerView.setAdapter(adapter1);
                recyclerView.getAdapter().notifyDataSetChanged();
                */

        progressDialog.show();

        metaDataCall = App.getApi().getMetaData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.computation())
                .subscribe(s -> {
                    progressDialog.dismiss();
                    Log.d(LOG_TAG, s.getTitle());
                    setTitle(s.getTitle());

                    recyclerView.setVerticalScrollBarEnabled(true);
                    recyclerView.setHorizontalScrollBarEnabled(true);

                    fields = s.getFields();
                    MetaDataAdapter adapter1 = new MetaDataAdapter(s.getFields(), getApplicationContext());
                    recyclerView.setAdapter(adapter1);
                    recyclerView.getAdapter().notifyDataSetChanged();
                });


        /*

        ____________on response

        Log.d("Answer", response.body().getResult());
                        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                        AlertDialog dialog;
                        alert.setTitle("Information");
                        alert.setMessage(response.body().getResult());

                        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        dialog = alert.create();
                        dialog.show();
                        progressDialog.dismiss();
                    }

         */


        sendButton.setOnClickListener(v -> {
            progressDialog.show();

            GsonBuilder builder = new GsonBuilder()
                    .setPrettyPrinting()
                    .registerTypeAdapter(Form.class, new FormSerializer())
                    .registerTypeAdapter(DataForSend.class, new DataSerializer())
                    .setLenient();
            Gson gson = builder.create();

            DataForSend dataForSend = new DataForSendBouilder().build();

            Log.d("JSON", gson.toJson(dataForSend));
            queryCall = App.getApi().getAnswer(gson.toJson(dataForSend))
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .unsubscribeOn(Schedulers.computation())
                    .subscribe(s -> {
                        Log.d("Answer", s.getResult());
                        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                        AlertDialog dialog;
                        alert.setTitle("Information");
                        alert.setMessage(s.getResult());

                        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        dialog = alert.create();
                        dialog.show();
                        progressDialog.dismiss();
                    });
        });
    }

    private void configRecyclerView() {
        linearManager = new LinearLayoutManager(getApplicationContext());
        recyclerView = (RecyclerView) findViewById(R.id.data_recycle_view);
        recyclerView.setLayoutManager(linearManager);
        MetaDataAdapter adapter = new MetaDataAdapter(fields, getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void configProgressDialog() {
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Downloading");
        progressDialog.setMessage("wait...");
        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                metaDataCall.dispose();
                queryCall.dispose();
            }
        });
    }
}