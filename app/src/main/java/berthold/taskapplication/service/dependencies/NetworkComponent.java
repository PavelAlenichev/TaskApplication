package berthold.taskapplication.service.dependencies;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by User on 27.03.2017.
 */
@Singleton
@Component(modules = NetworkModule.class)
public interface NetworkComponent {

    Retrofit retrofit();
}
