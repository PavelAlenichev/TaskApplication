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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import javax.inject.Inject;

import berthold.taskapplication.data.application.TaskApplication;
import berthold.taskapplication.data.metadata.Field;
import berthold.taskapplication.data.sending_data.DataForSend;
import berthold.taskapplication.data.sending_data.Form;
import berthold.taskapplication.serializers.DataSerializer;
import berthold.taskapplication.serializers.FormSerializer;
import berthold.taskapplication.service.DataForSendBuilder;
import berthold.taskapplication.service.MetaDataAdapter;
import berthold.taskapplication.service.dependencies.ClevertecApi;
import berthold.taskapplication.service.factories.Factory;
import berthold.taskapplication.service.factories.ViewFactory;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = "PavelLogs";
    private RecyclerView.LayoutManager linearManager;

    private RecyclerView recyclerView;
    private List<Field> fields;

    private Button sendButton;

    private ProgressDialog progressDialog;

    private Disposable metaDataCall;
    private Disposable queryCall;
    private Factory factory;

    @Inject
    ClevertecApi clevertecApi;


    /**
     * Создание активити, создание кнопок, отправка запроса на получение метаданных
     * отображение метаданных в RecyclerView, сериализация в Json-файл и отправка
     * на сервер по нажатию кнопки Send
     *
     * Получение ответа с сервера и отображение в AlertDialog
     *
     * Все комментарии добавлены лишь для улучшения понимания кода, логирование в некоторых местах -
     * Самопроверка
     *
     * Код-стайла я придерживался стандартного, но с элементами google - комментарии к каждым методам
     * И классам
     *
     * Замечание по поводу поворота экрана: оно поддерживается стандартно, т.к. я использовал версию
     * Retrofit'a, где есть поддержка смены ориентации во время запроса
     *
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((TaskApplication) getApplication())
                .getApiComponent()
                .inject(MainActivity.this); // Внедрение зависимостей

        configRecyclerView(); // Конфигурация RecyclerView

        sendButton = (Button) findViewById(R.id.sendButton);

        configProgressDialog(); // Конфигурация анимации ожидания

        progressDialog.show(); // Пока идет получение данных с сервера, отображать анимацию ожидания
        factory = new ViewFactory(); // Создание нужной Factory для дальнейшего использования

        metaDataCall = clevertecApi.getMetaData() // Получение мета-информации с сервера и обработка
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.computation())
                .subscribe(s -> {
                    progressDialog.dismiss(); // Отключение анимации по получении запроса и далее
                                              // построение формы
                    Log.d(LOG_TAG, s.getTitle());
                    setTitle(s.getTitle());

                    recyclerView.setVerticalScrollBarEnabled(true);
                    recyclerView.setHorizontalScrollBarEnabled(true);

                    fields = s.getFields();
                    MetaDataAdapter adapter1 = new MetaDataAdapter(s.getFields(),
                            factory, getApplicationContext());
                    recyclerView.setAdapter(adapter1);
                    recyclerView.getAdapter().notifyDataSetChanged();
                });


        sendButton.setOnClickListener(v -> {
            progressDialog.show();  // До получения ответа с сервера - отображение анимации

            GsonBuilder builder = new GsonBuilder()
                    .setPrettyPrinting()
                    .registerTypeAdapter(Form.class, new FormSerializer(factory))
                    .registerTypeAdapter(DataForSend.class, new DataSerializer())
                    .setLenient();
            Gson gson = builder.create();

            DataForSend dataForSend = new DataForSendBuilder(factory).build();

            Log.d("JSON", gson.toJson(dataForSend));
            queryCall = clevertecApi.getAnswer(gson.toJson(dataForSend)) // Получение ответа с сервера
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

    /**
     * Конфигурация RecyclerView
     * Вынесена из onCreate()
     */
    private void configRecyclerView() {
        linearManager = new LinearLayoutManager(getApplicationContext());
        recyclerView = (RecyclerView) findViewById(R.id.data_recycle_view);
        recyclerView.setLayoutManager(linearManager);
        MetaDataAdapter adapter = new MetaDataAdapter(fields, factory, getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * Конфигурация ProgressDialog
     * Вынесена из onCreate()
     */
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