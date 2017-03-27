package berthold.taskapplication.service.dependencies;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by User on 27.03.2017.
 */
@Module
public class ApiModule {

    @Provides
    @CustomScope
    ClevertecApi provideClevertecApi(Retrofit retrofit) {
        return retrofit.create(ClevertecApi.class);
    }
}
