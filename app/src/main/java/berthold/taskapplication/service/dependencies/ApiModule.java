package berthold.taskapplication.service.dependencies;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Создает уже натсроенный клиент ретрофита
 */
@Module
public class ApiModule {

    /**
     *
     * @param retrofit с заданными настройками
     * @return Retrofit клиент
     */
    @Provides
    @CustomScope
    ClevertecApi provideClevertecApi(Retrofit retrofit) {
        return retrofit.create(ClevertecApi.class);
    }
}