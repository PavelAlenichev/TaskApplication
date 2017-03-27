package berthold.taskapplication.service.dependencies;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Модуль, предоставляющий клиент ретрофита
 * Хардкод для базового адреса
 */
@Module
public class NetworkModule {

    private String baseUrl = "http://test.clevertec.ru/tt/";
    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    /**
     * Настраивает retrofit для создания. использует дефолтный url, google gson конвертер
     * RxJava2CallAdapter для обработки принятых данных с помощью rxJava
     *
     * @return настроенный retrofit
     */
    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}