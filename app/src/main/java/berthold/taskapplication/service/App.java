package berthold.taskapplication.service;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by User on 23.03.2017.
 */

public class App extends Application {

    private static ClevertecApi api;

    public static final String BASE_URL = "http://test.clevertec.ru/tt/";


    private static Retrofit retrofit;
    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        api = retrofit.create(ClevertecApi.class);
    }

    public static ClevertecApi getApi() {
        return api;
    }
}